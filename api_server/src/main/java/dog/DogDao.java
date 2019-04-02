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
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("SELECT * FROM dogs WHERE id = ?");) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        foundDog = dogFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return foundDog;
  }

  /**
   * Retrieve the information about a Dog for a given name/breed
   * @param name The name of the Dog.
   * @param breed The breed of the Dog.
   * @return A Dog object corresponding to the database entry for the given name/breed.
   */
  public List<Dog> get(String name, String breed) {
    List<Dog> foundDogs = new ArrayList<>();

    if ((name == null || name == "") && (breed == null || breed == "")) {
      return foundDogs;
    }

    else if((name != null && name != "") && (breed != null && breed != "")) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM dogs WHERE (name = ? OR ? IS NULL) AND (breed = ? OR ? IS NULL)");) {
          preparedStatement.setString(1, name);
          preparedStatement.setString(2, name);
          preparedStatement.setString(3, breed);
          preparedStatement.setString(4, breed);
          ResultSet resultSet = preparedStatement.executeQuery();

          while(resultSet.next()) {
            foundDogs.add(dogFromResultSet(resultSet));
          }
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }
        return foundDogs;
      }

      else if((name != null && name != "") && (breed == null || breed == "")) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM dogs WHERE name = ?");) {
          preparedStatement.setString(1, name);
          ResultSet resultSet = preparedStatement.executeQuery();

          while(resultSet.next()) {
            foundDogs.add(dogFromResultSet(resultSet));
          }
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }
          return foundDogs;
      }

      else {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM dogs WHERE breed = ?");) {
          preparedStatement.setString(1, breed);
          ResultSet resultSet = preparedStatement.executeQuery();

          while(resultSet.next()) {
            foundDogs.add(dogFromResultSet(resultSet));
          }
          } catch (SQLException e) {
          System.out.println(e.getMessage());
          }
          return foundDogs;
      }
  }

  /**
   * Retrieve a list of all dogs in the database.
   * @return A List of all Dog records.
   */
  @Override
  public List<Dog> getAll() {
    List<Dog> allDogs = new ArrayList<>();
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dogs");) {
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
  public Dog save(Dog dog) {
    Dog savedDog = null;

    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("INSERT INTO dogs (name, breed, age, owner_id, image_url) VALUES (?, ?, ?, ?, ?) RETURNING *");) {
      preparedStatement.setString(1, dog.getName());
      preparedStatement.setString(2, dog.getBreed());
      preparedStatement.setInt(3, dog.getAge());
      preparedStatement.setInt(4, dog.getOwnerId());
      preparedStatement.setString(5, dog.getImageUrl());
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();

      if (resultSet.next()) {
        savedDog = dogFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return savedDog;
  }

  /**
   * Updates an existing dog record in the database.
   * @param dog The Dog to update in the database. It's id will correspond to the id of the database record to be updated.
   */
  @Override
  public Dog update(Dog dog) {
    Dog updatedDog = null;

    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("UPDATE dogs SET name = ?, breed = ?, age = ?, owner_id = ?, image_url = ? WHERE id = ? RETURNING *");) {
      preparedStatement.setString(1, dog.getName());
      preparedStatement.setString(2, dog.getBreed());
      preparedStatement.setInt(3, dog.getAge());
      preparedStatement.setInt(4, dog.getOwnerId());
      preparedStatement.setString(5, dog.getImageUrl());
      preparedStatement.setInt(6, dog.getId());
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();

      if (resultSet.next()) {
        updatedDog = dogFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage() );
    }
    return updatedDog;
  }

  /**
   * Delete a dog record from the database.
   * @param dog The Dog to be deleted from the database. It's id will correspond to the id of the database record to be deleted.
   */
  @Override
  public void delete(Dog dog) {
    // Do nothing for now, deletion tricky to implement for the time being (what happens to active auctions?)
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
    Integer dogOwnerId = resultSet.getInt("owner_id");
    String dogImageUrl = resultSet.getString("image_url");
    return new Dog(dogId, dogName, dogBreed, dogAge, dogOwnerId, dogImageUrl);
  }

  public List<Dog> getUserDogs(int userId) {
    List<Dog> userDogs = new ArrayList<>();
    try (Connection connection = Database.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dogs WHERE owner_id = ?");) {
      preparedStatement.setInt(1, userId);
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        userDogs.add(dogFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return userDogs;
  }
}
