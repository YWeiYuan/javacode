/*
 * Copyright 2012-2022
 */
package com.xl.goahead.sso.client.filter;

import com.mzlion.easyokhttp.HttpClient;
import com.xl.goahead.sso.client.constant.AuthConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 客户端退出登录Filter
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/6/2 4:22 下午
 */
public class LogoutFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        Object token = session.getAttribute(AuthConst.TOKEN);
        if (AuthConst.LOGOUT.equals(httpRequest.getRequestURI()) && token != null) {
            // 向认证中心发送注销请求
            String logoutUrl = config.getInitParameter(AuthConst.LOGOUT_URL);
            String result = HttpClient.post(logoutUrl)
                    .param(AuthConst.TOKEN, String.valueOf(token))
                    .execute()
                    .asString();
            // 注销本地会话







        }
    }

    @Override
    public void destroy() {
    }
}
