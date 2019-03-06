package com.endsound.trpg.utakaze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@ComponentScan("com.endsound.trpg")
public class UtakazeApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtakazeApplication.class, args);
    }

}

