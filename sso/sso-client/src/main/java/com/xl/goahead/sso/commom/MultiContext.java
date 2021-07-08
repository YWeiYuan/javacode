/*
 * Copyright 2012-2022
 */
package com.xl.goahead.sso.commom;

import java.util.HashMap;
import java.util.Map;

/**
 * 多线程上下文
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/6/7 10:43 上午
 */
public class MultiContext {
    private static final ThreadLocal<Map<String, Object>> localMap = new ThreadLocal<Map<String, Object>>();

    public static void setAttribute(String key, Object value) {
        if (key == null || key.length() == 0) {
            return;
        }
        if (localMap.get() == null) {
            localMap.set(new HashMap<String, Object>(16));
        }
        localMap.get().put(key, value);
    }

    public static Object getAttribute(String key) {
        if (key == null || key.length() == 0 ) {
            return null;
        }
        if (localMap.get() == null) {
            localMap.set(new HashMap<String, Object>(16));
        }
        return localMap.get().get(key);
    }

    public static void setAttributes(Map<String, Object> attributes) {
        localMap.set(attributes);
    }

    public static Map<String, Object> getAttributes() {
        if (localMap.get() == null) {
            localMap.set(new HashMap<String, Object>(16));
        }
        return localMap.get();
    }

    public static void removeAttribute(){
        localMap.remove();
    }
}
