package com.demo.solr.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
public class SpringSolrApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringSolrApplication.class, args);
	}
	
	@SuppressWarnings("deprecation")
	private ApiInfo apiDetails(){
		return new ApiInfo(
				"DB Data Solr Index UI API",
				"API Documentation of DSIU Application",
				"1.0",
				"Free To Use",
				"Anshu Gupta",
				"API License",
				"localhost:8081"
		);
	}
	
	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.demo"))
				.build()
				.apiInfo(apiDetails());
	}
	

	
	
}


