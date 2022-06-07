package com.joyintech.tams.svreport.chb.assetinfodetail.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wubingbing
 * @date 2022-06-07 10:11
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ParamFilter());
        registration.addUrlPatterns("/chb/assetinfodetail/AssetInfoDetail001/export");
        registration.setOrder(1);
        return registration;
    }
}
