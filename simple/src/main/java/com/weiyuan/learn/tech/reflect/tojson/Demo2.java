/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.tech.reflect.tojson;

import com.google.gson.Gson;

/**
 * Gson解析泛型对象时TypeToken的使用方法
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 10:14 下午
 */
public class Demo2 {
    public static void main(String[] args) {
        // 没问题
        String jsonData = "{\n" +
                "    \"name\": \"BeJson\"}";
        Gson gson = new Gson();
        DataBean bean = gson.fromJson(jsonData, DataBean.class);
        System.out.println("bean name: " + bean.name);
        System.out.println("bean jsonStr: " + gson.toJson(bean));

        //
        Foo<DataBean> foo = new Foo<DataBean>();
        foo.value = bean;
        //测试中使用gson 2.2.4版本，序列化正常
        System.out.println("foo jsonStr: " + gson.toJson(foo));

        String genericData = "{\"value\":{\"name\":\"BeGeneric\"}}";
        Foo<DataBean> genericBean = gson.fromJson(genericData, foo.getClass());
        //反序列化失败，genericBean.value的类型被反序列化为com.google.gson.internal.LinkedTreeMap。运行时报错
        System.out.println("generic bean value : " + genericBean.value.toString());
    }

    class DataBean {
        public String name;
    }

    static class Foo<T> {
        T value;
    }
}
