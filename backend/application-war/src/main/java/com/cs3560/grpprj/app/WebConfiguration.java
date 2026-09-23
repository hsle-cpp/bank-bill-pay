package com.cs3560.grpprj.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** WORK AREA: application-war | OWNER: Lead | Shared web policy; feature modules must not add ad-hoc CORS rules. */
@Configuration
class WebConfiguration implements WebMvcConfigurer {
  @Override public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**").allowedOrigins("http://localhost:8081", "http://localhost:19006").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
  }
}
