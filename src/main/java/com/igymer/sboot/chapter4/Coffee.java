package com.igymer.sboot.chapter4;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coffee {

  @Id
  private String id;
  private String name;

  public Coffee() {
  }

  public Coffee(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public Coffee(String name) {
    this(UUID.randomUUID().toString(), name);
  }

  public String getId() {
    return id;
  }

  public Coffee withId(String id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Coffee withName(String name) {
    this.name = name;
    return this;
  }
}
