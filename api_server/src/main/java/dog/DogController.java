package dog;

import io.javalin.Handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DogController {

  private static DogDao dogDao = new DogDao();

  public static Handler getAll = ctx -> ctx.json(dogDao.getAll());

  /**
   * JSON Body Syntax:
   * {
   *   "name": "testName",
   *   "breed": "testBreed",
   *   "age": 10,
   *   "ownerId": 1
   * }
   */
  public static Handler create = ctx -> {
    Dog newDog = ctx.validatedBodyAsClass(Dog.class).getOrThrow();
    List<String> errors = newDog.validate();
    if (!errors.isEmpty()) {
      Map<String, Object> errorsMap = new HashMap<>();
      errorsMap.put("status", 400);
      errorsMap.put("errors", errors);
      ctx.status(400).json(errorsMap);
    } else {
      Dog savedDog = dogDao.save(newDog);
      ctx.json(savedDog);
    }
  };

  public static Handler update = ctx -> {
    Integer id = Integer.parseInt(ctx.pathParam(":id"));
    Dog dog = dogDao.get(id);
    if (dog == null) {
      Map<String, Object> message = new HashMap<>();
      message.put("status", 404);
      message.put("details", "Dog not found for ID " + id.toString());
      ctx.status(404).json(message);
    } else {
      Dog updatedDog = ctx.bodyAsClass(Dog.class);
      dog.update(updatedDog);
      Dog newDog = dogDao.update(dog);
      ctx.json(newDog);
    }
  };

  public static Handler get = ctx -> {
    Integer id = Integer.parseInt(ctx.pathParam(":id"));
    Dog dog = dogDao.get(id);
    if (dog == null) {
      Map<String, Object> message = new HashMap<>();
      message.put("status", 404);
      message.put("details", "Dog not found for ID " + id.toString());
      ctx.status(404).json(message);
    } else {
      ctx.json(dog);
    }
  };

  public static Handler getOne = ctx -> {
    String name = ctx.queryParam("name");
    String breed = ctx.queryParam("breed");
    List<Dog> dogs = dogDao.get(name, breed);
    if (dogs.size() < 1) {
      Map<String, Object> message = new HashMap<>();
      message.put("status", 404);
      message.put("details", "Dog not found for name " + name + " and breed " + breed);
      ctx.status(404).json(message);
    } else {
      ctx.json(dogs);
    }
  };

  public static Handler getUserDogs = ctx -> {
    Integer userId = Integer.parseInt(ctx.pathParam(":id"));
    List<Dog> dogs = dogDao.getUserDogs(userId);
    ctx.json(dogs);
  };

}
