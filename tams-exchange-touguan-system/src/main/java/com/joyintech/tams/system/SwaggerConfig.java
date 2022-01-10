package com.joyintech.tams.system;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicates;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import springfox.documentation.builders.ParameterBuilder;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.service.Parameter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	private static ApiInfo apiInfo;

	static {
		apiInfo("场内指令子系统", "场内指令子系统V3.0.4版本", "V3.0.4");
	}

	public static void apiInfo(String title, String description, String version, String termsOfServiceUrl,
			Contact contact, String license, String licenseUrl) {
		apiInfo = new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl);
	}

	public static void apiInfo(String title, String description, String version) {
		apiInfo(title, description, version, "", ApiInfo.DEFAULT_CONTACT,
				System.getProperty("license"), "");
	}

	public static void apiInfo(String version) {
		apiInfo("Api Documentation", "Api Documentation", version);
	}

	@SuppressWarnings("unchecked")
	public static Docket createDocket(String groupName, String basePackage) {
//		ParameterBuilder session = new ParameterBuilder();
//		ParameterBuilder channel = new ParameterBuilder();
//		List<Parameter> pars = new ArrayList<Parameter>();
//		session.name("sessionid").description("令牌").modelRef(new ModelRef("string")).parameterType("header")
//				.required(false).build();
//		channel.name("channel").description("PC").modelRef(new ModelRef("string")).parameterType("header")
//				.required(false).build();
//		pars.add(session.build());
//		pars.add(channel.build());

		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		if (apiInfo != null) {
			docket.apiInfo(apiInfo);
			docket.host(apiInfo.getTermsOfServiceUrl());
		}
		return docket.groupName(groupName).select().paths(PathSelectors.any())
//	                 .apis(RequestHandlerSelectors.basePackage(basePackage))
				.apis(Predicates.and(withMethodAnnotation(ApiOperation.class), withClassAnnotation(Api.class),
						basePackage(basePackage)))
				.build();
				//.globalOperationParameters(pars);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		super.addResourceHandlers(registry);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
}
