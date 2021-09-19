/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.thirdapi;

import com.mzlion.easyokhttp.HttpClient;

/**
 * 高德天气预报
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 9:06 下午
 */
public class AmapRestApi {
    final static private String KEY = "***";
    /**
     * 高德地图接口Url
     */
    final static private String AMAP_URL = "https://restapi.amap.com";

    final static private String IP_LOCAL_URL = "v5/ip";

    /**
     * IP定位 2.0
     *
     * @param ip
     * @return
     */
    public static String ipLocation(String ip) {
        String url = AMAP_URL + "/" + IP_LOCAL_URL;
        return HttpClient.get(url)
                .queryString("key", KEY)
                .queryString("type", 4)
                .queryString("ip", ip)
                .asString();
    }

    public static void main(String[] args) throws InterruptedException {
        String result = AmapRestApi.ipLocation("183.253.5.234");
        Thread.sleep(1111);
        String ew = AmapRestApi.ipLocation("183.253.5.24");
        System.out.println(result);
        System.out.println(ew);

    }


}
