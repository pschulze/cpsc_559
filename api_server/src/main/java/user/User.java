package user;

import java.util.ArrayList;
import java.util.List;

public class User {
  private String username;
  private Integer id;

  public String getUsername() {
    return username;
  }

  public Integer getId() {
    return id;
  }

  public User(String username) {
    this.username = username;
  }

  public User(Integer id, String username) {
    this.id = id;
    this.username = username;
  }

  public List<String> validate() {
    List<String> errors = new ArrayList<>();
    if (this.username == null)
      errors.add("username is null");

    UserDao userDao = new UserDao();
    if (userDao.getByUsername(this.username) != null)
      errors.add("user with username: " + this.username + " already exists");

    return errors;
  }
}
