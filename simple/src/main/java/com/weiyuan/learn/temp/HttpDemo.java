/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.temp;


import com.mzlion.core.http.ContentType;
import com.mzlion.easyokhttp.HttpClient;
import com.mzlion.easyokhttp.response.HttpResponse;
import com.weiyuan.learn.io.serialize.vo.Person;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * https://gitee.com/mzllon/easy-okhttp
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 7:54 下午
 */
public class HttpDemo {
    public static void main(String[] args) {
        // 普通的GET请求无参数
        String responseData = HttpClient
                // 请求方式和请求url
                .get("http://localhost:8080/user-sys/user/list")
                .asString();
        // 普通的GET请求带参数
        String responseData2 = HttpClient
                // 请求方式和请求url
                .get("http://localhost:8080/user-sys/user/list")
                //设置请求参数
                .queryString("mobile", "18018110018")
                .asString();
        // POST普通表单提交
        String responseData3 = HttpClient
                // 请求方式和请求url
                .post("http://localhost:8080/user-sys/user/add")
                // 表单参数
                .param("name", "张三")
                .param("mobile", "13023614020")
                .param("langs", "Java")
                .param("langs", "Python")
                //url参数
                //queryString("queryTime","20160530")
                .asString();

        // POST提交String
        String responseData4 = HttpClient
                // 请求方式和请求url
                .textBody("http://localhost:8080/user-sys/user/body1")
                .text("设施一串和服务端约定好的数据格式")
                //设置编码
                //.charset("utf-8")
                .asString();
        // POST提交JSON格式的文本
        String responseData5 = HttpClient
                // 请求方式和请求url
                .textBody("http://localhost:8080/user-sys/user/import")
                // post提交json
                .json("[{\"name\": \"test-13\",\"mobile\": \"18321001200\",\"programLangs\": \"Java,Pyhton\",\"remark\": \"0\"}]")
                //设置编码
                .asString();
        // POST提交XML等其他格式的文本
        String responseData6 = HttpClient
                // 请求方式和请求url
                .textBody("http://localhost:8080/user-sys/user/body2")
                //post提交xml
                .xml("<?xml version=\"1.0\" encoding=\"utf-8\" ?>")
                //post提交html
                //.html("function fun(){}")
                //post提交一段javascript
                //.javascript("function fn(){}")
                //设置编码
                //.charset("utf-8")
                .asString();
        // POST提交二进制文件
        String responseData7 = HttpClient
                // 请求方式和请求url
                .binaryBody("http://localhost:8080/user-sys/user/body3")
                // post提交流
                .stream(HttpDemo.class.getClassLoader().getResourceAsStream("avatar.png"))
                //设置请求内容类型
                .contentType(ContentType.IMAGE_JPG)
                //post提交文件
                //.file(new File("d:/avatar.png"))
                .asString();
        //ContentType内置常见的MIME类型，基本上不用自己创建

        // POST表单提交含文件上传
        String responseData8 = HttpClient
                // 请求方式和请求url
                .post("http://localhost:8080/user-sys/user/add")
                .param("name", "李四")
                .param("mobile", "13023614021")
                .param("avatarFile", HttpDemo.class.getClassLoader().getResourceAsStream("avatar.png"), "avatar.png")
                //.param("avatarFile", new File("D:/avatar.png")
                .asString();

        // POST提交支持一个参数设置多个值或替换
        String responseData9 = HttpClient
                // 请求方式和请求url
                .post("http://localhost:8080/user-sys/user/add")
                // 表单参数
                .param("name", "张三")
                .param("mobile", "13023614020")
                .param("langs", "Java")
                //会多种语言
                .param("langs", "Python")
                .asString();


        //
        HttpResponse httpResponse = HttpClient
                // 请求方式和请求url
                .get("http://localhost:8080/user-sys/user/add")
                .execute();

        //将响应结果转为文件保存
        File frc = new File("d:\\web\\save.txt");
        httpResponse.asFile(frc);


        //重载方法
        Person person = httpResponse.asBean(Person.class);

        // 将响应结果转为输出流中
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        httpResponse.asStream(baos);

        // https
        //由信任CA机构发布的，比如GitHub的https，框架不需要你做什么事情，正常使用即可
        String githubContent = HttpClient
                .get("https://www.mzlion.com")
                .asString();

        //不管三七二十几，直接忽略HTTPS
        String mzlionIndexContent = HttpClient
                .get("https://kyfw.12306.cn/otn/")
                .customSSL()
                .asString();

        //自签SSL或程序不认可实际安全的，可以指定客户端证书
        String r = HttpClient
                .get("https://kyfw.12306.cn/otn/")
                .customSSL(HttpClient.class.getClassLoader().getResourceAsStream("SRCA.cer"))
                .asString();
    }
}
