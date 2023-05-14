/*
 * Copyright 2016-2021 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.automvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Kingstar
 * @since  2.1
 */
//@SpringBootApplication
//public class Application extends SpringBootServletInitializer {
//默认只使用单数据源; 所以用多个数据源时,要关闭         如何在java代码中关闭???
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {
	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Application.class);
//	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		
//		return new WebMvcConfigurerAdapter() {
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						//.allowedOrigins(ip)  //可访问ip，ip最好从配置文件中获取，
//						.allowedMethods("PUT", "DELETE", "GET", "POST").allowedHeaders("*")
//						.exposedHeaders("access-control-allow-headers", "access-control-allow-methods", "access-control-allow-origin", "access-control-max-age", "X-Frame-Options")
//						.allowCredentials(false).maxAge(3600);
//			}
//		};
//
//	}
}
