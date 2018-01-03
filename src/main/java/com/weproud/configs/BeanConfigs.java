package com.weproud.configs;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author Logan. 81k
 */
@Configuration
public class BeanConfigs {
    @Bean
    public ObjectMapper apiObjectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .featuresToEnable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JavaTimeModule())
                .build();
    }
}
