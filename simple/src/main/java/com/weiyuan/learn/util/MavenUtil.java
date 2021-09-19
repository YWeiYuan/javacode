/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.util;

import java.io.File;
import java.io.IOException;

/**
 * 清除maven本地仓库中lastUpdated文件以及_remote.repositories
 * 解决jar下载问题
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 8:40 下午
 */
public class MavenUtil {
    /**
     * 遍历指定目录下（包括其子目录）的所有文件，并删除以 lastUpdated 结尾的文件
     *
     * @param dir 目录的位置 path
     * @throws IOException
     */
    public static void listDirectory(File dir) throws IOException {
        if (!dir.exists()) {
            throw new IllegalArgumentException("目录：" + dir + "不存在.");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + " 不是目录。");
        }
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //递归
                    listDirectory(file);
                } else { // 删除以 lastUpdated 结尾的文件
                    String fileName = file.getName();
                    boolean isLastupdated = fileName.toLowerCase().endsWith("lastupdated");
                    boolean b = fileName.toLowerCase().endsWith("_remote.repositories");
                    if (isLastupdated || b) {
                        boolean isDel = file.delete();
                        System.out.println("删除的文件名 => " + file.getName() + "  || 是否删除成功？ ==> " + isDel);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 指定maven的本地仓库
        listDirectory(new File("/Users/huanglongquan/.m2/repository/"));

    }
}
