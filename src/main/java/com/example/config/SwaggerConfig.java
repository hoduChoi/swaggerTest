package com.example.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
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
		          .build();
		          //.apiInfo(apiInfo());
		          
		          //.useDefaultResponseMessages(apply) default message 사용 여
		          //.globalResponseMessage(RequestMethod.GET, getArrayList()); //기본 default response message가 있는데 그걸 제어할 수도 있음.
																			   //모든 GET, POST 메소드의 response message의 의미 설정할 수 있고 각각의 API의 response 또한 제어 가능.
	}

//	private ArrayList<ResponseMessage> getArrayList() {
//		 ArrayList<ResponseMessage> lists = new ArrayList<ResponseMessage>();
//         
//	        lists.add(new ResponseMessageBuilder().code(500).message("서버에러").responseModel(new ModelRef("Error")).build());
//	        lists.add(new ResponseMessageBuilder().code(403).message("권한없음").responseModel(new ModelRef("Forbbiden")).build());
//	         
//	        return lists;
//	}

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
	
//	private ApiInfo apiInfo() {
//		return new ApiInfo("API명", 
//							"API의 description", 
//							"Swagger version", 
//							"terms of service", 
//							new Contact("컨택할 사람이름", "", "이메일"), 
//							"라이센스", 
//							"라이센스 url");
//	}
}
