/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.juc.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe类的深入理解
 * 可以用来在任意内存地址位置处读写数据，可见，对于普通用户来说，使用起来还是比较危险的
 * @author longquan.huang
 * @version 1.0
 * @date 2021/5/20 11:13 上午
 */
public class UnsafeTest {
    public static void main(String[] args) {
        allocateInstance();
    }


    /**
     * 获取unsafe对象,不能通过new Unsafe 以及 getUnsafe()方法获取，
     * 因为 unsafe被设计成单利模式，并且只能通过类加载器进行调用 可以查看源码 Unsafe.getUnsafe()的实现
     * 我们可以通过反射来加载，Unsafe类有个私有静态变量theUnsafe
     * @return unsafe对象
     */
    private static Unsafe getUnsafeByReflect() {
        try {
            // 通过反射得到theUnsafe对应的对象
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            // 设置field 可以访问
            theUnsafe.setAccessible(true);
            // 通过Field得到该Field对应的对象，传入null是因为该Field是static
            return  (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * unsafe allocateInstance方法，不会调用构造方法
     */
    public static void allocateInstance() {
        Unsafe unsafe = getUnsafeByReflect();
        Test1 test1 = new Test1();
        System.out.println(test1.getA());
        try {
            Test1 test11 = Test1.class.newInstance();
            Integer a = test11.getA();
            System.out.println(a);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            if (unsafe != null) {
                Test1 o = (Test1)unsafe.allocateInstance(Test1.class);
                System.out.println(o.getA());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
    static class Test1 {
        private Integer a = 0;

        public Test1() {
            this.a = 1;
        }

        public Integer getA() {
            return a;
        }
    }
}
