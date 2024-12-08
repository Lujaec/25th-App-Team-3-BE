package org.yapp.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (requests) -> requests
                                .anyRequest().authenticated()
                )
                .oauth2Login((configurer) ->
                        configurer
                                .redirectionEndpoint(endpoint -> endpoint.baseUri("/login/oauth2/callback/"))
                                .defaultSuccessUrl("/home", true)
                                .failureUrl("/login?error=true")
                )
                .headers(configurer -> configurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(csrf -> csrf.disable())
                .formLogin(formLogin -> formLogin.disable());
        return http.build();
    }
}