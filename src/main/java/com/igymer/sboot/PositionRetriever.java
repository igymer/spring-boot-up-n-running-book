package com.igymer.sboot;

import java.util.List;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class PositionRetriever {

  private final AircraftRepository repo;

  @Bean
  Consumer<List<Aircraft>> retrieveAircraftPositions() {
    return acList -> {
      repo.deleteAll();
      repo.saveAll(acList);
      repo.findAll().forEach(System.out::println);
    };
  }

}
