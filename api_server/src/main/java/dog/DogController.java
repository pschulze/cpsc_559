package dog;

import io.javalin.Handler;

import java.util.HashMap;
import java.util.Map;

public class DogController {

  private static DogDao dogDao = new DogDao();

  public static Handler getAll = ctx -> ctx.json(dogDao.getAll());

  public static Handler create = ctx -> {
    Dog newDog = ctx.validatedBodyAsClass(Dog.class).check(Dog::isValid).getOrThrow();
    dogDao.save(newDog);
    ctx.json(newDog);
  };

  public static Handler update = ctx -> {
    Long id = Long.parseLong(ctx.pathParam(":id"));
    Dog dog = dogDao.get(id);
    if (dog == null) {
      Map<String, String> message = new HashMap<>();
      message.put("status", "404");
      message.put("details", "Dog not found for ID " + id.toString());
      ctx.status(404).json(message);
    } else {
      Dog updatedDog = ctx.bodyAsClass(Dog.class);
      dog.update(updatedDog);
      dogDao.update(dog);
      ctx.json(dog);
    }
  };

  public static Handler get = ctx -> {
    Long id = Long.parseLong(ctx.pathParam(":id"));
    Dog dog = dogDao.get(id);
    if (dog == null) {
      Map<String, String> message = new HashMap<>();
      message.put("status", "404");
      message.put("details", "Dog not found for ID " + id.toString());
      ctx.status(404).json(message);
    } else {
      ctx.json(dog);
    }
  };
}
