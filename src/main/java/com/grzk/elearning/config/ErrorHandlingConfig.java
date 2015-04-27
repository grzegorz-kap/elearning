package com.grzk.elearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import cz.jirutka.spring.exhandler.RestHandlerExceptionResolver;
import cz.jirutka.spring.exhandler.support.HttpMessageConverterUtils;

@Configuration
public class ErrorHandlingConfig {
	
	@Autowired
	private MessageSourceConfig messageSourceConfig;
	
	@Bean
	public RestHandlerExceptionResolver restExceptionResolver(){
		return RestHandlerExceptionResolver.builder()
				.messageSource(messageSourceConfig.validationMessageSource())
				.defaultContentType(MediaType.APPLICATION_JSON)
				.addErrorMessageHandler(EmptyResultDataAccessException.class, HttpStatus.NOT_FOUND)
				//.addHandler(MyException.class,new MyExceptionHandler);
				.withDefaultMessageSource(false)
				.build();
	}
	
	
	@Bean
	public MessageSource httpErrorMessageSource() {
		return messageSourceConfig.validationMessageSource();
	}

	@Bean
	public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver(){
		ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
		resolver.setMessageConverters(HttpMessageConverterUtils.getDefaultHttpMessageConverters());
		
		return resolver;
	}
}
