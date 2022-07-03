package com.igymer.sboot.chapter3;

import java.util.ArrayList;
import java.util.List;
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

  private final List<Coffee> coffees = new ArrayList<>();

  public RestApiDemoController() {
    coffees.addAll(List.of(
        new Coffee("Sort 1"),
        new Coffee("Sort 2"),
        new Coffee("Sort 3"),
        new Coffee("Sort 4")
    ));
  }

  @GetMapping()
  Iterable<Coffee> getCoffees() {
    return coffees;
  }

  @GetMapping("/{id}")
  Optional<Coffee> getCoffeeById(@PathVariable String id) {
    return coffees.stream()
        .filter(coffee -> id.equals(coffee.getId()))
        .findAny();
  }

  @PostMapping()
  Coffee postCoffee(@RequestBody Coffee coffee) {
    coffees.add(coffee);
    return coffee;
  }

  //Обновлять ресурс при наличии. Иначе создавать
  @PutMapping("/{id}")
  ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
    int index = -1;
    for (Coffee element : coffees) {
      if (element.getId().equals(id)) {
        index = coffees.indexOf(element);
        coffees.set(index, coffee);
      }
    }

    return index == -1 ?
        new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
        new ResponseEntity<>(coffee, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  void deleteCoffee(@PathVariable String id) {
    coffees.removeIf(c -> c.getId().equals(id));
  }
}
