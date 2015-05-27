package com.grzk.elearning.config;

import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { 
				MvcConfig.class, SecurityConfig.class , ServiceConfig.class, JPAConfig.class
				};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic reg){
		reg.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		reg.setInitParameter("encoding", "UTF-8");
		reg.setInitParameter("forceEncoding", "true");
	}

}
