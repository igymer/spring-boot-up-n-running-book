package com.igymer.sboot;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Component
class PlaneFinderPoller {

  private WebClient client =
      WebClient.create("http://localhost:7634/aircraft");

  private final AircraftRepository repository;

  PlaneFinderPoller(AircraftRepository repository) {
    this.repository = repository;
  }

  @Scheduled(fixedRate = 1000)
  private void pollPlanes() {

    client.get()
        .retrieve()
        .bodyToFlux(Aircraft.class)
        .filter(plane -> !plane.getReg().isEmpty())
        .toStream()
        .forEach(repository::save);

    repository.findAll().forEach(System.out::println);
  }
}
