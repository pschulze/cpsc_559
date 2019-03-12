package dog;

public class Dog {

  private Long id;
  private String name;
  private String breed;
  private Integer age;
  private String ownerName;

  public Long getId() {
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

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  /**
   * Create a new dog object without an id. Must be added to the database and be assigned an id before it will be valid.
   * @param name The name of the new dog.
   * @param breed The breed of the new dog.
   * @param age The age of the new dog.
   * @param ownerName The name of the current owner of the dog.
   */
  public Dog(String name, String breed, Integer age, String ownerName) {
    this.name = name;
    this.breed = breed;
    this.age = age;
    this.ownerName = ownerName;
  }

  public Dog(Long id, String name, String breed, Integer age, String ownerName) {
    this.id = id;
    this.name = name;
    this.breed = breed;
    this.age = age;
    this.ownerName = ownerName;
  }

  /**
   * Ensures all the fields necessary to save a new Dog to the database are present.
   * @return True if the dog can be saved to the database, false if it cannot be.
   */
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

    // Validate ownerName
    if (this.ownerName == null) {
      valid = false;
    }

    return valid;
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

    if (updatedDog.ownerName !=  null)
      this.ownerName = updatedDog.ownerName;
  }
}
