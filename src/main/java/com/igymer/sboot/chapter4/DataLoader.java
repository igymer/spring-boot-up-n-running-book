package com.igymer.sboot.chapter4;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

  private final CoffeeRepository repository;

  public DataLoader(CoffeeRepository repository) {
    this.repository = repository;
  }

  @PostConstruct
  private void loadData() {
    repository.saveAll(List.of(
        new Coffee("Sort 1"),
        new Coffee("Sort 2"),
        new Coffee("Sort 3"),
        new Coffee("Sort 4")
    ));
  }
}
