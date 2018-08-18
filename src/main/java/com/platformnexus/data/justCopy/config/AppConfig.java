package com.platformnexus.data.justCopy.config;

import com.platformnexus.data.justCopy.util.TokenGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public TokenGenerator tokenGenerator() {
        return new TokenGenerator(10);
    }
}