package com.einfochips.currencyexchange.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
	@SuppressWarnings("deprecation")
	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(
				new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		registrationBean.addUrlMappings("/resources/*");
		return registrationBean;
	}
}
