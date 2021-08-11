package com.joyintech.sso;

import java.io.IOException;

import org.springframework.beans.PropertyValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.bind.PropertySourcesPropertyValues;
import org.springframework.boot.env.PropertySourcesLoader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import com.joyintech.framework.config.WebApplication;
import com.joyintech.system.SystemApplication;

/**
 * 系统启动类
 */
@Configuration
public class SSOApplication extends WebApplication {

	public static void main(String[] args) throws Exception {
    	initLog();
    	initSpring(args);
    }

	private static ConfigurableApplicationContext initSpring(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SystemApplication.class, args);
		return context;
	}

    /**
     * 启动类兼容资管0.0.3框架
     */
    private static void initLog() {
        PropertySourcesLoader propertiesLoader = new PropertySourcesLoader();
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/application-framework.yml");
        try {
        	PropertySource<?> propertySource = propertiesLoader.load(resource, null, "application", null);
        	MutablePropertySources propertySources = new MutablePropertySources();
        	propertySources.addFirst(propertySource);
        	PropertySourcesPropertyValues values = new PropertySourcesPropertyValues(propertySources, false);
        	PropertyValue value = values.getPropertyValue("log.path");
        	if (null != value) {
        		System.setProperty("logfolder", (String)value.getValue());
        	} else {
        		System.setProperty("logfolder", "logs");
        	}
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }
    
}