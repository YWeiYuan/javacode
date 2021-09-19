/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.tech.reflect.tojson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Gson通过借助TypeToken获取泛型参数的类型的方法
 * https://www.jianshu.com/p/cdea9a8db18b
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 10:14 下午
 */
public class Demo3 {
    public static void main(String[] args) {
        String genericData = "{\"value\":{\"name\":\"BeGeneric\"}}";
        TypeToken<Foo<DataBean>> typeToken = new TypeToken<Foo<DataBean>>(){};
        Gson gson = new Gson();
        Foo<DataBean> genericBean = gson.fromJson(genericData, typeToken.getType());
        System.out.println("generic bean value : " + gson.toJson(genericBean.value));
    }

    class DataBean {
        public String name;
    }

    class Foo<T> {
        T value;
    }
}
