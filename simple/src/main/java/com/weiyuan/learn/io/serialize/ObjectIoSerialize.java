/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.io.serialize;

import com.weiyuan.learn.io.serialize.vo.Person;

import java.io.*;

/**
 * io对象实例化
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/8/15 12:08 上午
 */
public class ObjectIoSerialize {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        writeObject();
        readObject();
    }

    /**
     * 写对象到文件中，文件写完后会乱码
     *
     * @throws IOException
     */
    public static void writeObject() throws IOException {
        // 写对象到文件中
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("oos.txt"));
        Person person = new Person("longquan.huang", 18);
        // Person 需要继承 Serializable，不然writeObject会报NotSerializableException异常
        oos.writeObject(person);
        oos.close();
    }

    /**
     * 从文件中读取对象
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("oos.txt"));
        Object o = ois.readObject();
        System.out.println(o);
    }
}
