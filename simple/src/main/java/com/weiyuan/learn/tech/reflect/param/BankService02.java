/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.tech.reflect.param;

import com.mzlion.easyokhttp.HttpClient;
import com.weiyuan.learn.tech.reflect.param.annotations.BankAPI;
import com.weiyuan.learn.tech.reflect.param.annotations.BankAPIField;
import com.weiyuan.learn.tech.reflect.param.pojo.CreateUserApi;
import com.weiyuan.learn.tech.reflect.param.pojo.PayApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 案例场景
 * <p>
 * 假设银行提供了一些 API 接口，对参数的序列化有点特殊，不使用 JSON，而是需要我们把参数依次拼在一起构成一个大字符串
 * 按照银行提供的API文档顺序，将所有的参数构成定长的数据，并且拼接在一起作为一整个字符串
 * 因为每一种参数都有固定长度，未达到长度需要进行填充处理
 * 字符串类型参数不满长度部分要以下划线右填充，即字符串内容靠左
 * 数字类型的参数不满长度部分以0左填充，即实际数字靠右
 * 货币类型的表示需要把金额向下舍入2位到分，以分为单位，作为数字类型同样进行左填充
 * 参数做MD5 操作作为签名
 * <p>
 * 参考： https://mp.weixin.qq.com/s/PONOlM240WlWz58nUgy9Bg
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 7:07 下午
 */
@Slf4j
public class BankService02 {
    /**
     * 创建用户方法
     *
     * @param name
     * @param identity
     * @param mobile
     * @param age
     * @return
     * @throws IOException
     */
    public static String createUser(String name, String identity, String mobile, int age) throws IOException {
        CreateUserApi createUserApi = new CreateUserApi();
        createUserApi.setName(name);
        createUserApi.setIdentity(identity);
        createUserApi.setMobile(mobile);
        createUserApi.setAge(age);
        return remoteCall(createUserApi);
    }

    /**
     * 支付方法
     *
     * @param userId
     * @param amount
     * @return
     * @throws IOException
     */
    public static String pay(long userId, BigDecimal amount) throws IOException {
        PayApi payApi = new PayApi();
        payApi.setAmount(amount);
        payApi.setUserId(userId);
        return remoteCall(payApi);
    }


    /**
     * 封装网络请求方法
     *
     * @param api
     * @return
     * @throws IOException
     */
    private static String remoteCall(AbstractApi api) throws IOException {
        //从BankAPI注解获取请求地址
        BankAPI bankApi = api.getClass().getAnnotation(BankAPI.class);
        StringBuilder stringBuilder = new StringBuilder();
        //获得所有字段
        Arrays.stream(api.getClass().getDeclaredFields())
                //查找标记了注解的字段
                .filter(field -> field.isAnnotationPresent(BankAPIField.class))
                //根据注解中的order对字段排序
                .sorted(Comparator.comparingInt(a -> a.getAnnotation(BankAPIField.class).order()))
                //设置可以访问私有字段
                .peek(field -> field.setAccessible(true))
                .forEach(field -> {
                    //获得注解
                    BankAPIField bankApiField = field.getAnnotation(BankAPIField.class);
                    Object value = "";
                    try {
                        //反射获取字段值
                        value = field.get(api);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    //根据字段类型以正确的填充方式格式化字符串
                    switch (bankApiField.type()) {
                        case "S": {
                            stringBuilder.append(String.format("%-" + bankApiField.length() + "s", value.toString()).replace(' ', '_'));
                            break;
                        }
                        case "N": {
                            stringBuilder.append(String.format("%" + bankApiField.length() + "s", value.toString()).replace(' ', '0'));
                            break;
                        }
                        case "M": {
                            if (!(value instanceof BigDecimal)) {
                                String text = String.format("%s 的 %s 必须是BigDecimal", api, field);
                                throw new RuntimeException(text);
                            }
                            stringBuilder.append(String.format("%0" + bankApiField.length() + "d", ((BigDecimal) value).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
                            break;
                        }
                        default:
                            break;
                    }
                });
        //签名逻辑
        stringBuilder.append(DigestUtils.md5DigestAsHex(stringBuilder.toString().getBytes()));
        String param = stringBuilder.toString();
        long begin = System.currentTimeMillis();
        //发请求
        String result = HttpClient.textBody("http://localhost:45678/reflection" + bankApi.url())
                .text(stringBuilder.toString())
                //设置编码
                //.charset("utf-8")
                .asString();
        log.info("调用银行API {} url:{} 参数:{} 耗时:{}ms", bankApi.desc(), bankApi.url(), param, System.currentTimeMillis() - begin);
        return result;
    }

}
