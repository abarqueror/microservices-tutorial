package com.tutorial.userservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    //AHora usamos EUREKA:Es necesario al haber quitado url:port y sustituirlas por el service-name
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
