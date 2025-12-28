package com.example.SpringConcepts.config;

import com.example.SpringConcepts.component.AutomaticWashingMachine;
import com.example.SpringConcepts.component.ManualWashingMachine;
import com.example.SpringConcepts.component.WashingMachine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public WashingMachine washingMachine(){
        return new AutomaticWashingMachine();
    }
}
