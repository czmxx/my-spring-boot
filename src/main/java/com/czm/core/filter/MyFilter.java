package com.czm.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器
 * Created by Administrator on 2017/2/18.
 * 过滤器属于Servlet范畴的API
 */
@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class MyFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger("MyFilter");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        log.info("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info(request.getRequestURI() + "_---------------------------");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

        log.info("destroy" + System.currentTimeMillis());
    }
}
