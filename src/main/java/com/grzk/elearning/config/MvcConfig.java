package com.grzk.elearning.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.grzk.elearning.*" })
@Import(value = { ThymeleafConfig.class, MessageSourceConfig.class,
		ErrorHandlingConfig.class })
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private ErrorHandlingConfig errorHandlingConfig;

	@Autowired
	private MessageSourceConfig messageSourceConfig;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
		registry.addResourceHandler("/public/**").addResourceLocations(
				"classpath:/public/");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureHandlerExceptionResolvers(
			List<HandlerExceptionResolver> resolvers) {
		resolvers.add(errorHandlingConfig.exceptionHandlerExceptionResolver());
		resolvers.add(errorHandlingConfig.restExceptionResolver());
	}

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator
				.setValidationMessageSource((MessageSource) messageSourceConfig
						.validationMessageSource());
		return validator;
	}

}
