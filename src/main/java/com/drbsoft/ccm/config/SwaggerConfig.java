package com.drbsoft.ccm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicates;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private @Value("${info.app.title}") String title;
	private @Value("${info.app.description}") String description;
	private @Value("${info.app.version}") String version;
	private @Value("${info.app.author}") String author;
	private @Value("${info.app.email}") String email;
	private @Value("${info.app.url}") String url;

	@Bean
	public Docket documentation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.drbsoft.ccm.controller"))
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.build()
				.apiInfo(metadata());
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder()
			.title(title)
			.description(description)
			.version(version)
			.contact(new Contact(author, url, email))
			.build();
	}
}