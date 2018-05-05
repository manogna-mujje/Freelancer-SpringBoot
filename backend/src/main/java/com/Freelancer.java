package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@SpringBootApplication

public class Freelancer {
	public static void main(String[] args){
        SpringApplication.run(Freelancer.class,args);
    }
	
	 @Bean
	    public FilterRegistrationBean corsFilterRegistration() {
	        FilterRegistrationBean registrationBean =
	                new FilterRegistrationBean(new CORSFilter());
	        registrationBean.setName("CORS Filter");
	        registrationBean.addUrlPatterns("/*");
	        registrationBean.setOrder(1);
	        return registrationBean;
	 }
}

//@Bean
//public WebMvcConfigurer corsConfigurer() {
//    return new WebMvcConfigurerAdapter() {
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/checkSession").allowedOrigins("http://localhost:8080");
//        }
//    };
//}

