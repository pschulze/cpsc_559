package user;

import io.javalin.Handler;
import java.util.HashMap;
import java.util.Map;

public class UserController {
  private static UserDao userDao = new UserDao();

  public static Handler getAll = ctx -> ctx.json(userDao.getAll());

  public static Handler get = ctx -> {
    String username = ctx.pathParam(":username");
    User user = userDao.get(username);
    if (user == null) {
      Map<String, String> message = new HashMap<>();
      message.put("status", "404");
      message.put("details", "User not found for username " + username);
      ctx.status(404).json(message);
    } else {
      ctx.json(userDao.get(username));
    }
  };

  public static Handler create = ctx -> {
    User newUser = ctx.validatedBodyAsClass(User.class).check(User::isValid).getOrThrow();
    userDao.save(newUser);
    ctx.json(newUser);
  };

  public static Handler delete = ctx -> {
    String username = ctx.pathParam(":username");
    User userToDelete = userDao.get(username);
    if (userToDelete == null) {
      Map<String, String> message = new HashMap<>();
      message.put("status", "404");
      message.put("details", "User not found for username " + username);
      ctx.status(404).json(message);
    } else {
      userDao.delete(userToDelete);
      ctx.json(userToDelete);
    }
  };
}
