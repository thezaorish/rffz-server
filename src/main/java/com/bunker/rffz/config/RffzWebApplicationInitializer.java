package com.bunker.rffz.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Web application Java configuration class. The usage of web application initializer requires Spring Framework 3.1 and Servlet 3.0.
 * @author Victor Balanica
 */
public class RffzWebApplicationInitializer implements WebApplicationInitializer {

	private static final String DISPATCHER_SERVLET_NAME = "rffzServlet";
	private static final String DISPATCHER_SERVLET_MAPPING = "/";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(WebContextConfig.class, PersistenceContextConfig.class, ScheduledTaskContextConfig.class);
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);

		servletContext.addListener(new ContextLoaderListener(rootContext));
	}
	
}
