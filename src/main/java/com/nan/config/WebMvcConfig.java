package com.nan.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/login").setViewName("login");
    	registry.addViewController("/logout").setViewName("login");
		registry.addViewController("/welcome").setViewName("index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		WebMvcConfigurer.super.addViewControllers(registry);
	}
	
	/**
	 * 上传组件
	 * @return
	 */
	@Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        return multipartResolver;
    }

	/**
	 * 跨域配置
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
        .allowedOrigins("*")
        .allowCredentials(true)
        .allowedMethods("GET", "POST", "DELETE", "PUT")
        .maxAge(3600);
	}

	/**
	 * 内容协商机制
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
	}

	/**
	 * 路径匹配
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseRegisteredSuffixPatternMatch(true);
	}

	@SuppressWarnings("rawtypes")
	@Bean
	public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(
				dispatcherServlet);
		//servletServletRegistrationBean.addUrlMappings("*.html");

		return servletServletRegistrationBean;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/"); 
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/file/**").addResourceLocations("classpath:/static/file/");
		registry.addResourceHandler("/html/**").addResourceLocations("classpath:/static/html/");
		registry.addResourceHandler("/icon/**").addResourceLocations("classpath:/static/icon/");
		registry.addResourceHandler("/image/**").addResourceLocations("classpath:/static/image/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
	}

}
