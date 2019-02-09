package dog;

import io.javalin.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class DogController {

  // Example dogs
  private static ArrayList<Dog> dogs = new ArrayList<>(Arrays.asList(
      new Dog(UUID.randomUUID().toString(), "Scruffy", "Golden Retriever", 3),
      new Dog(UUID.randomUUID().toString(), "Seymour", "Mutt", 15),
      new Dog(UUID.randomUUID().toString(), "Clifford", "Big Red", 59)
  ));

  public static Handler getAll = ctx -> ctx.json(dogs);

  public static Handler create = ctx -> {
    Dog newDog = ctx.validatedBodyAsClass(Dog.class).check(Dog::isValid).getOrThrow();
    dogs.add(newDog);
    ctx.json(newDog);
  };

  public static Handler get = ctx -> {
    dogs.forEach(dog -> {
      if (dog.getId().equals(ctx.pathParam(":id"))) {
        ctx.json(dog);
      }
    });
  };

  public static Handler update = ctx -> {
    // Update code here
  };
}
