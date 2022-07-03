package com.igymer.sboot.chapter4;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffees")
public class RestApiDemoController {

  private final CoffeeRepository repository;

  public RestApiDemoController(CoffeeRepository repository) {
    this.repository = repository;
  }

  @GetMapping()
  Iterable<Coffee> getCoffees() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  Optional<Coffee> getCoffeeById(@PathVariable String id) {
    return repository.findById(id);
  }

  @PostMapping()
  Coffee postCoffee(@RequestBody Coffee coffee) {
    return repository.save(coffee);
  }

  //Обновлять ресурс при наличии. Иначе создавать
  @PutMapping("/{id}")
  ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
    return repository.existsById(id) ?
        new ResponseEntity<>(coffee, HttpStatus.OK) :
        new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  void deleteCoffee(@PathVariable String id) {
    repository.deleteById(id);
  }
}
