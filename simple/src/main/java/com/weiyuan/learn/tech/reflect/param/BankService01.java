/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.tech.reflect.param;

import com.mzlion.easyokhttp.HttpClient;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 7:07 下午
 */
public class BankService01 {
    /**
     * 创建用户方法
     * @param name
     * @param identity
     * @param mobile
     * @param age
     * @return
     * @throws IOException
     */
    public static String createUser(String name, String identity, String mobile, int age) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //字符串靠左，多余的地方填充_
        stringBuilder.append(String.format("%-10s", name).replace(' ', '_'));
        //字符串靠左，多余的地方填充_
        stringBuilder.append(String.format("%-18s", identity).replace(' ', '_'));
        //数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%05d", age));
        //字符串靠左，多余的地方用_填充
        stringBuilder.append(String.format("%-11s", mobile).replace(' ', '_'));
        //最后加上MD5作为签名
        stringBuilder.append(DigestUtils.md5DigestAsHex(stringBuilder.toString().getBytes()));
        return HttpClient.textBody("http://localhost:45678/reflection/bank/createUser")
                .text(stringBuilder.toString())
                //设置编码
                //.charset("utf-8")
                .asString();
    }

    /**
     * 支付方法
     * @param userId
     * @param amount
     * @return
     * @throws IOException
     */
    public static String pay(long userId, BigDecimal amount) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%020d", userId));
        //金额向下舍入2位到分，以分为单位，作为数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%010d", amount.setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
        //最后加上MD5作为签名
        stringBuilder.append(DigestUtils.md5DigestAsHex(stringBuilder.toString().getBytes()));
        return HttpClient.textBody("http://localhost:45678/reflection/bank/pay")
                .text(stringBuilder.toString())
                //设置编码
                //.charset("utf-8")
                .asString();
    }

}
