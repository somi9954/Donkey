package org.project.donkey.configs.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(org.project.donkey.configs.jwt.JwtProperties.class)
public class JwtConfig {
    @Bean
    public org.project.donkey.configs.jwt.TokenProvider tokenProvider(org.project.donkey.configs.jwt.JwtProperties jwtProperties) {
        return new org.project.donkey.configs.jwt.TokenProvider(jwtProperties.getSecret(), jwtProperties.getAccessTokenValidityInSeconds());
    }
}