package com.schoolmanager.schoolhub.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SchoolConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
