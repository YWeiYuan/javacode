/*
 * Copyright 2012-2022
 */
package com.xl.goahead.sso.client.util;

import com.xl.goahead.sso.client.constant.AuthConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session管理
 * @author longquan.huang
 * @version 1.0
 * @date 2021/6/2 5:28 下午
 */
public class SessionUtil {
    /**
     * 判断是否用户是否已经登录
     * @param request
     * @return
     */
    public static boolean isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(AuthConst.IS_LOGIN) != null){
            return true;
        }
        return false;
    }


    
}
