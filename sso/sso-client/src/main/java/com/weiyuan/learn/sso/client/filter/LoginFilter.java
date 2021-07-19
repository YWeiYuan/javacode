/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.sso.client.filter;

import com.mzlion.core.lang.StringUtils;
import com.weiyuan.learn.sso.client.constant.AuthConst;
import com.weiyuan.learn.sso.client.util.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 客户端登录Filter
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/6/2 4:22 下午
 */
public class LoginFilter implements Filter {

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
        // 如果session已经存在登录，&& 未过期 就放行
        if (SessionUtil.isLogin(httpRequest)) {
            chain.doFilter(request, response);
            return;
        }
        // 从认证中心回跳的带有token的请求，从认证中心获取授权用户信息，保存到本身session中
        // 这里的token是通过get的方式传递过来的。从cookie会因为跨域问题导致数据传输失败。
        String token = request.getParameter(AuthConst.TOKEN);
        if (!StringUtils.isEmpty(token)) {
            // 从认证中心 获取 授权信息, 并保存到session中
        }
        // 重新定向至登录页面，并附带当前请求地址，方便跳转
        httpResponse.sendRedirect(config.getInitParameter(AuthConst.LOGIN_URL) + "?" + AuthConst.CLIENT_URL + "=" + httpRequest.getRequestURL());
    }

    @Override
    public void destroy() {
    }

}
