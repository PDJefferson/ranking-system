package com.rankingsystem.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;


@Configuration
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/h2-console/**")
                .antMatchers("/css/**", "/js/**", "/images/**",
                        "/webjars/**", "/**/*.html", "/**/*.css", "/**/*.js") //to ignore interface files
                .antMatchers("/api/**");

    }
}
