/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.tech.reflect.tojson;

import com.google.gson.Gson;

/**
 * 日常文本转成成bean对象很常见，但泛型类就会有问题。
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 10:09 下午
 */
public class Demo1 {
    public static void main(String[] args) {
        String jsonData = "{\n" +
                "    \"name\": \"BeJson\"}";
        Gson gson = new Gson();
        DataBean bean = gson.fromJson(jsonData, DataBean.class);
        System.out.println(bean.name);
    }
    class DataBean{
        public String name;
    }
}
