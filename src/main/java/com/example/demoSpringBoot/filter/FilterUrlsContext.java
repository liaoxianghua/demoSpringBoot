package com.example.demoSpringBoot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created
 *
 * @author liaoxianghua
 */

//@Component
public class FilterUrlsContext implements InitializingBean {


    //自定义路径


    private Logger log = LoggerFactory.getLogger(FilterUrlsContext.class);


    //默认路径
    public  static  List<String> defaultUrls = new ArrayList<String>(){
        {
            add("");
        }
    };

    @Override
    public void afterPropertiesSet() throws Exception {
        //自定义过滤路径 = /aa,/bb,/cc
//        String[] urls = filterUrl.split(",");
//        if (urls != null && Arrays.asList(urls) != null){
//            defaultUrls.addAll(Arrays.asList(urls));
//        }
    }
}
