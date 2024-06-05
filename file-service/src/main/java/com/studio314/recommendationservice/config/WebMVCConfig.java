package com.studio314.recommendationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Value("${me.file.upload-path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 访问路径以 “/mystatic” 开头时，会去 “mystatic” 路径下找静态资源
        registry
                .addResourceHandler("/file/source/**")
                .addResourceLocations("file:" + path);
    }

}
