package com.example.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)  
		          .select()                                  
		          .apis(RequestHandlerSelectors.basePackage("com.example.api"))   //RequestHandlerSelectors.any()           
		          .paths(PathSelectors.ant("/board/**"))                         //PathSelectors.any()
		          .build()
		          .apiInfo(apiInfo());  
	}

	//만일 swagger ui 경로 커스텀 원한다면 리다이렉트.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/jechoi/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
		registry.addRedirectViewController("/jechoi/swagger-resources/configuration/ui","/swagger-resources/configuration/ui");
		registry.addRedirectViewController("/jechoi/swagger-resources/configuration/security","/swagger-resources/configuration/security");
		registry.addRedirectViewController("/jechoi/swagger-resources", "/swagger-resources");
	}
	
	//스프링 부트가 아니면 사용자는 리소스 핸들러의 자동설정 혜택을 받을 수 없으므로 명시.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	    registry.addResourceHandler("swagger-ui.html")
//	      .addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/jechoi/**")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo("API명", 
							"API의 description", 
							"Swagger version", 
							"terms of service", 
							new Contact("컨택할 사람이름", "", "이메일"), 
							"라이센스", 
							"라이센스 url");
	}
}
