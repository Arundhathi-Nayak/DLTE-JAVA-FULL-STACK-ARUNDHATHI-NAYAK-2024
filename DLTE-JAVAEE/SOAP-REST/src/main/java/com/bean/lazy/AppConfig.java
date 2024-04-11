package com.bean.lazy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {
    @Bean
 //   @Lazy
    public Bank bank() {
        return new Bank();
    }
}
