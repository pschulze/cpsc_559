package user;

import io.javalin.Handler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
  private static UserDao userDao = new UserDao();

  public static Handler getAll = ctx -> ctx.json(userDao.getAll());

  public static Handler get = ctx -> {
    Integer id = Integer.parseInt(ctx.pathParam(":id"));
    User user = userDao.get(id);
    if (user == null) {
      Map<String, String> message = new HashMap<>();
      message.put("status", "404");
      message.put("details", "User not found for id " + id);
      ctx.status(404).json(message);
    } else {
      ctx.json(user);
    }
  };

  /**
   * JSON Body syntax:
   * {
   *   "username": "testUser"
   * }
   */
  public static Handler create = ctx -> {
    User newUser = ctx.validatedBodyAsClass(User.class).getOrThrow();
    List<String> errors = newUser.validate();
    if (!errors.isEmpty()) {
      Map<String, Object> errorsMap = new HashMap<>();
      errorsMap.put("status", 400);
      errorsMap.put("errors", errors);
      ctx.status(400).json(errorsMap);
    } else {
      User savedUser = userDao.save(newUser);
      ctx.json(savedUser);
    }
  };

  public static Handler delete = ctx -> {
    Integer id = Integer.parseInt(ctx.pathParam(":id"));
    User userToDelete = userDao.get(id);
    if (userToDelete == null) {
      Map<String, Object> message = new HashMap<>();
      message.put("status", 404);
      message.put("details", "User not found for id " + id);
      ctx.status(404).json(message);
    } else {
      userDao.delete(userToDelete);
      ctx.json(userToDelete);
    }
  };
}
