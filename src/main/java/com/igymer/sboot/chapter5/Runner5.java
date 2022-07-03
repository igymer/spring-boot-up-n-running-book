package com.igymer.sboot.chapter5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Runner5 {

  public static void main(String[] args) {
    SpringApplication.run(Runner5.class, args);
  }

  @Bean
  @ConfigurationProperties(prefix = "droid")
  Droid getDroid() {
    return new Droid();
  }
}
