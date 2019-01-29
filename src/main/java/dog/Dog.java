package dog;

import java.util.UUID;

public class Dog {

  private String id;
  private String name;
  private String breed;
  private Integer age;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Dog() {
    this.id = UUID.randomUUID().toString();
  }

  public Dog(String name, String breed, Integer age) {
    this();
    this.name = name;
    this.breed = breed;
    this.age = age;
  }

  public Dog(String id, String name, String breed, Integer age) {
    this.id = id;
    this.name = name;
    this.breed = breed;
    this.age = age;
  }

  public Boolean isValid() {
    boolean valid = true;

    // Validate name
    if (this.name == null)
      valid = false;

    // Validate breed
    if (this.breed == null)
      valid = false;

    // Validate age
    if (this.age == null || this.age < 0)
      valid = false;

    // Validate id
    if (this.id == null)
      valid = false;

    return valid;
  }
}
