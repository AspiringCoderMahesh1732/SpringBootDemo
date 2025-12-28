package com.example.SpringConcepts.config;

import com.example.SpringConcepts.Interceptor.OrderControllerInterceptor;
import com.example.SpringConcepts.Interceptor.WashingMachineRequestModificationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    WashingMachineRequestModificationInterceptor washingMachineRequestModificationInterceptor;
    @Autowired
    OrderControllerInterceptor orderControllerInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(washingMachineRequestModificationInterceptor)
                .addPathPatterns("/demo-interceptor");
    }
}
