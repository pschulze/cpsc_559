package dog;

import java.util.ArrayList;
import java.util.List;

import user.UserDao;

public class Dog {

  private Integer id;
  private String name;
  private String breed;
  private Integer age;
  private Integer ownerId;
  private String imageUrl;

  public Integer getId() {
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

  public Integer getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Integer ownerId) {
    this.ownerId = ownerId;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  /**
   * Create a new dog object without an id. Must be added to the database and be assigned an id before it will be valid.
   * @param name The name of the new dog.
   * @param breed The breed of the new dog.
   * @param age The age of the new dog.
   * @param OwnerId The name of the current owner of the dog.
   */
  public Dog(String name, String breed, Integer age, Integer ownerId) {
    this.name = name;
    this.breed = breed;
    this.age = age;
    this.ownerId = ownerId;
  }

  public Dog(Integer id, String name, String breed, Integer age, Integer ownerId, String imageUrl) {
    this.id = id;
    this.name = name;
    this.breed = breed;
    this.age = age;
    this.ownerId = ownerId;
    this.imageUrl = imageUrl;
  }

  /**
   * Ensures all the fields necessary to save a new Dog to the database are present.
   * @return True if the dog can be saved to the database, false if it cannot be.
   */
  public List<String> validate() {
    List<String> errors = new ArrayList<>();

    // Validate name
    if (this.name == null)
      errors.add("name is null");

    // Validate breed
    if (this.breed == null)
      errors.add("breed is null");

    // Validate age
    if (this.age == null)
      errors.add("age is null");
    else if (this.age < 0)
      errors.add("age is invalid");

    // Validate ownerId
    if (this.ownerId == null)
      errors.add("ownerId is null");
    else if (new UserDao().get(this.ownerId) == null)
      errors.add("owner with id: " + this.ownerId.toString() + " does not exist");

    return errors;
  }

  /**
   * Update a Dog with the non-null fields of a newer Dog.
   * @param updatedDog The Dog containing fields to be applied to the existing Dog.
   */
  public void update(Dog updatedDog) {
    if (updatedDog.name != null)
      this.name = updatedDog.name;

    if (updatedDog.breed != null)
      this.breed = updatedDog.breed;

    if (updatedDog.age != null)
      this.age = updatedDog.age;

    if (updatedDog.ownerId !=  null)
      this.ownerId = updatedDog.ownerId;

    if (updatedDog.imageUrl != null)
      this.imageUrl = updatedDog.imageUrl;
  }
}
