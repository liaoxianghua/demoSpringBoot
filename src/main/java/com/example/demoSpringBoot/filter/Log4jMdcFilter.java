package com.example.demoSpringBoot.filter;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;

//@Order(1)
//@WebFilter(filterName = "log4jMdcFilter", urlPatterns = "/*")
public class Log4jMdcFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(Log4jMdcFilter.class);

    @Value("${logback.mdc.serialNoFilterUrl}")
    private String filterUrl;
    ArrayList<String> list=new ArrayList<String>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String[] filterUrls = new String[]{};
        if (filterUrl != null) {
            filterUrls = filterUrl.split(",");
        }
        if (filterUrls.length > 0) {
            for (int i = 0; i < filterUrls.length; i++) {
                list.add(filterUrls[i]);
            }
        }
        if (!list.contains(filterUrl)) {
            list.add(filterUrl);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SerialNoStrategyGenerator serialNoStrategyGenerator = new SerialNoStrategyGenerator();
        if (list.contains(servletRequest.getServletContext())){
            MDC.put("THREAD_ID", serialNoStrategyGenerator.generate());
            logger.info("default url", JSONObject.toJSONString(list));
        }
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("进入filter");
    }

    @Override
    public void destroy() {
        MDC.clear();
    }
}
