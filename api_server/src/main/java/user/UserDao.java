package user;

import data.Dao;
import data.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Perform database operations relating to the User class.
 */
public class UserDao implements Dao<User, Integer> {

  /**
   * Retrieve the information about a User for a given username.
   * @param username The username of the user
   * @return A User object corresponding to the entry for the given username.
   */
  public User get(Integer id) {
    User foundUser = null;
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("SELECT * FROM users where id = ?");) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        foundUser = userFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return foundUser;
  }

  public User getByUsername(String username) {
    User foundUser = null;
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("SELECT * FROM users WHERE username = ?");) {
      preparedStatement.setString(1, username);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        foundUser = userFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return foundUser;
  }

  /**
   * Retrieve a list of all users in the database.
   * @return A list of all the User records.
   */
  public List<User> getAll() {
    List<User> allUsers = new ArrayList<>();
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("SELECT * FROM users");) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        allUsers.add(userFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return  allUsers;
  }

  /**
   * Save a new User object to the database.
   * @param user The newly created User to be saved.
   */
  public User save(User user) {
    User savedUser = null;
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("INSERT INTO users (username) VALUES (?) RETURNING *");) {
      preparedStatement.setString(1, user.getUsername());
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();

      if (resultSet.next()) {
        savedUser = userFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return savedUser;
  }

  /**
   * Updates an existing user record in the database.
   * @param user The User to be updated in the database.
   */
  public User update(User user) {
    return user;
    // Do nothing for now. The only field in the users table is username, and that's the primary key.
  }

  /**
   * Delete a user record from the database.
   * @param user The user to be deleted from the database. It's id will corr
   */
  public void delete(User user) {
    // Do nothing for now, deletion tricky to implement for the time being (make sure all necessary records are cleaned up)
  }

  /**
   * Build a new User object from the result of a database query.
   * @param resultSet Result of a database query with the cursor pointing to the user object to be built.
   * @return A new User object based off of the values of the record pointed to by the resultSet
   * @throws SQLException When the resultSet cursor does not point to a User record.
   */
  private User userFromResultSet(ResultSet resultSet) throws SQLException {
    String username = resultSet.getString("username");
    Integer id = resultSet.getInt("id");
    return new User(id, username);
  }
}
