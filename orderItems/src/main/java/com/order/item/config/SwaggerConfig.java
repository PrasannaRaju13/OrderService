package com.order.item.config;

import static com.order.item.constants.SwaggerConstants.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SuppressWarnings("rawtypes")
public class SwaggerConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler(SWAGGER_UI).addResourceLocations(RESOURCE_LOCATION);
		registry.addResourceHandler(WEB_JARS).addResourceLocations(WEB_JARS_LOCATION);
	}

	@Bean
	public Docket api() {
		final Parameter autherizationParam = new ParameterBuilder().name(AUTHORIZATION)
				.description(TOKEN_MSG).modelRef(new ModelRef(TYPE_STR)).parameterType(HEADER)
				.required(false).build();
		Parameter headerParam = new ParameterBuilder().name(CONTENT_TYPE).modelRef(new ModelRef(TYPE_STR)).parameterType(HEADER).required(true).defaultValue(MediaType.APPLICATION_JSON_VALUE).build();
        
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).paths(Predicates.not(PathSelectors.regex(ERROR_PATH))).paths(Predicates.not(PathSelectors.regex(ACTUATOR_PATH)))
				.build().apiInfo(metadata())
				.pathMapping(BACK_SLASH).globalOperationParameters(Arrays.asList(headerParam,autherizationParam));
	}
	

	private ApiInfo metadata() {
		final ApiInfo apiInfo = new ApiInfo(API_DESCRIPTION, API_NAME, VERSION, EMPTY_STRING,
				new springfox.documentation.service.Contact(ORDER_ITEM, ORDER_ITEM_URL, ORDER_ITEM_EMAIL),
				ORDER_ITEM, EMPTY_STRING, new ArrayList<VendorExtension>());
		return apiInfo;
	}

}
