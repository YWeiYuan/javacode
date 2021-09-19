/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.jvm;

/**
 * Demo demo = new Demo() 在内存做什么？
 *
 1.加载Demo.class 文件进内存（方法区）
 2.在栈空间为Demo demo变量开辟空间
 3.在堆空间为对象 new Demo()申请一个空间
 4.对象成员变量默认初始化，null，0
 5.对象成员变量进行显示初始化，longquan.huang，27
 6.执行构造方法给成员变量进行初始化，longquan，30
 7.对象初始化完毕，把堆内存地址赋值给栈内存中的demo变量
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/16 11:03 下午
 */
public class DemoMain {
    public static void main(String[] args) {
        Demo demo = new Demo();
    }
}

class Demo {
    private String name = "longquan.huang";
    private int age = 27;

    public Demo() {
        this.name = "longquan";
        this.age = 30;
    }
}
