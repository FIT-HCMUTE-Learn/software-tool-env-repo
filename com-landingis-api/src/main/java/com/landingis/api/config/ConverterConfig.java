package com.landingis.api.config;

import com.landingis.api.converter.StringToEnumConverter;
import com.landingis.api.enumeration.LearningState;
import com.landingis.api.enumeration.RegisterStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter((Converter<String, RegisterStatus>) new StringToEnumConverter<>(RegisterStatus.class));
        registry.addConverter((Converter<String, LearningState>) new StringToEnumConverter<>(LearningState.class));
    }
}
