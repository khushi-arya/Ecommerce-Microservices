package com.demo.APIgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                .authorizeExchange(exchange -> exchange

                        .pathMatchers("/users/login", "/users/register")
                        .permitAll()

                        .pathMatchers("/products/**")
                        .hasAnyRole("ADMIN", "CUSTOMER")

                        .pathMatchers("/inventory/add")
                        .hasRole("ADMIN")

                        .pathMatchers("/orders/**")
                        .hasRole("CUSTOMER")

                        .anyExchange()
                        .authenticated()
                )

                .build();
    }
}