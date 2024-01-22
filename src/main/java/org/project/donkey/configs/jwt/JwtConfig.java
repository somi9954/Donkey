package org.project.donkey.configs.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(gotogym.configs.jwt.JwtProperties.class)
public class JwtConfig {
    @Bean
    public gotogym.configs.jwt.TokenProvider tokenProvider(gotogym.configs.jwt.JwtProperties jwtProperties) {
        return new gotogym.configs.jwt.TokenProvider(jwtProperties.getSecret(), jwtProperties.getAccessTokenValidityInSeconds());
    }
}