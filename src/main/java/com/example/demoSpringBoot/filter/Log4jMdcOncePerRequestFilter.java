package com.example.demoSpringBoot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Configuration
public class Log4jMdcOncePerRequestFilter extends OncePerRequestFilter {


    private static final Logger logger= LoggerFactory.getLogger(Log4jMdcOncePerRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            SerialNoStrategyGenerator serialNoStrategyGenerator = new SerialNoStrategyGenerator();
            if (FilterUrlsContext.defaultUrls.contains(httpServletRequest.getServletPath())){
                MDC.put("THREAD_ID", serialNoStrategyGenerator.generate());
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }catch (Exception e){
            logger.error("过滤器Log4jMdcOncePerRequestFilter 异常",e);
        }finally {
            MDC.clear();
        }
    }

}
