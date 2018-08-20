package com.platformnexus.data.justCopy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JustCopyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JustCopyApplication.class, args);
    }
}
