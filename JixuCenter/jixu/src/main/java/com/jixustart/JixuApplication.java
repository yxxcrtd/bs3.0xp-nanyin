package com.jixustart;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Locale;

@SpringBootApplication(exclude=SecurityAutoConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({"com.joyintech.jixu"})
public class JixuApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) throws Exception{
		Locale local = new Locale("zh", "CN");
		Locale.setDefault(local);
		SpringApplication.run(JixuApplication.class, args);
	}
}
