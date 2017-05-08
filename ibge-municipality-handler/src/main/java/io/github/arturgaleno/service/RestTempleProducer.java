package io.github.arturgaleno.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by artur on 17/04/17.
 */
@Configuration
public class RestTempleProducer {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .messageConverters(Collections.singletonList(new GsonHttpMessageConverter()))
                .build();
    }

}
