package user;

public class User {
  private String username;

  public String getUsername() {
    return username;
  }

  public User(String username) {
    this.username = username;
  }

  public boolean isValid() {
    System.out.println("in is valid user");
    boolean valid = true;
    if (this.username == null)
      valid = false;

    UserDao userDao = new UserDao();
    if (userDao.get(this.username) != null)
      valid = false;

    return valid;
  }
}
