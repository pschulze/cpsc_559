package dog;

import data.Dao;
import data.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Perform database operations relating to the Dog class.
 */
public class DogDao implements Dao<Dog, Integer> {

  /**
   * Retrieve the information about a Dog for a given id
   * @param id The id of the Dog.
   * @return A Dog object corresponding to the database entry for the given id.
   */
  @Override
  public Dog get(Integer id) {
    Dog foundDog = null;
    try {
      Connection connection = Database.getConnection();
      PreparedStatement preparedStatement =
          connection.prepareStatement("SELECT * FROM dogs WHERE id = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      boolean found = resultSet.first();

      if (found) {
        foundDog = dogFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return foundDog;
  }

  /**
   * Retrieve a list of all dogs in the database.
   * @return A List of all Dog records.
   */
  @Override
  public List<Dog> getAll() {
    List<Dog> allDogs = new ArrayList<>();
    try {
      Connection connection = Database.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dogs");
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        allDogs.add(dogFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return allDogs;
  }

  /**
   * Save a new Dog object to the database.
   * @param dog The newly created Dog to be saved.
   */
  @Override
  public void save(Dog dog) {
    try {
      Connection connection = Database.getConnection();
      PreparedStatement preparedStatement =
          connection.prepareStatement("INSERT INTO dogs (name, breed, age, owner_name) VALUES (?, ?, ?, ?)");
      preparedStatement.setString(1, dog.getName());
      preparedStatement.setString(2, dog.getBreed());
      preparedStatement.setInt(3, dog.getAge());
      preparedStatement.setString(4, dog.getOwnerName());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Updates an existing dog record in the database.
   * @param dog The Dog to update in the database. It's id will correspond to the id of the database record to be updated.
   */
  public void update(Dog dog) {
    try {
      Connection connection = Database.getConnection();
      PreparedStatement preparedStatement =
          connection.prepareStatement("UPDATE dogs SET name = ?, breed = ?, age = ?, owner_name = ? WHERE id = ?");
      preparedStatement.setString(1, dog.getName());
      preparedStatement.setString(2, dog.getBreed());
      preparedStatement.setInt(3, dog.getAge());
      preparedStatement.setString(4, dog.getOwnerName());
      preparedStatement.setInt(5, dog.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage() );
    }
  }

  /**
   * Delete a dog record from the database.
   * @param dog The Dog to be deleted from the database. It's id will correspond to the id of the database record to be deleted.
   */
  public void delete(Dog dog) {
    // Do nothing for now, deletion tricky to implement for the time being (what happens to active auctions?)
//    try {
//      Connection connection = Database.getConnection();
//      PreparedStatement preparedStatement =
//          connection.prepareStatement("DELETE FROM dogs WHERE id = ?");
//      preparedStatement.Integer(1, dog.getId());
//      preparedStatement.executeUpdate();
//    } catch (SQLException e) {
//      System.out.println(e.getMessage());
//    }
  }

  /**
   * Build a Dog object from the result of a database query.
   * @param resultSet Result of a database query with the cursor pointing to the dog object to be built.
   * @return A new Dog object based off of values of the record pointed to by the resultSet.
   * @throws SQLException When the resultSet cursor does not point to a Dog record.
   */
  private Dog dogFromResultSet(ResultSet resultSet) throws SQLException {
    Integer dogId = resultSet.getInt("id");
    String dogName = resultSet.getString("name");
    String dogBreed = resultSet.getString("breed");
    Integer dogAge = resultSet.getInt("age");
    String dogOwnerName = resultSet.getString("owner_name");
    return new Dog(dogId, dogName, dogBreed, dogAge, dogOwnerName);
  }
}
