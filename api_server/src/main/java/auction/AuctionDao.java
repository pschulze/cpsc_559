package auction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import data.Dao;
import data.Database;

public class AuctionDao implements Dao<Auction, Integer> {

  @Override
  public Auction get(Integer id) {
    Auction foundAuction = null;
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("SELECT * FROM auctions WHERE id = ?");) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        foundAuction = auctionFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return foundAuction;
  }

  public List<Auction> get(String name) {
    List<Auction> auctions = new ArrayList<>();
    try (Connection connection = Database.getConnection();
         PreparedStatement preparedStatement =
                 connection.prepareStatement("SELECT * FROM auctions WHERE name = ?");) {
      preparedStatement.setString(1, name);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        auctions.add(auctionFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return auctions;
  }


  @Override
  public List<Auction> getAll() {
    List<Auction> allAuctions = new ArrayList<>();
    String currentTime = Instant.now().toString();
    try (Connection connection = Database.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM auctions WHERE auctions.expiration_time < ? ::timestamp");) {
      preparedStatement.setString(1, currentTime);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        allAuctions.add(auctionFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return allAuctions;
  }

  public List<Auction> getAllActive() {
    List<Auction> allAuctions = new ArrayList<>();
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM auctions WHERE completed = false");) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        allAuctions.add(auctionFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return allAuctions;
  }

  @Override
  public Auction save(Auction auction) {
    Auction savedAuction = null;
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("INSERT INTO auctions (expiration_time, dog_id, start_price, name, completed) VALUES (?, ?, ?, ?, ?) RETURNING *");) {
      preparedStatement.setTimestamp(1, Timestamp.from(auction.getExpirationTime()));
      preparedStatement.setInt(2, auction.getDogId());
      preparedStatement.setDouble(3, auction.getStartPrice());
      preparedStatement.setString(4, auction.getName());
      preparedStatement.setBoolean(5, auction.getCompleted());
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();

      if (resultSet.next()) {
        savedAuction = auctionFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return savedAuction;
  }

  @Override
  public Auction update(Auction auction) {
    Auction updatedAuction = null;
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("UPDATE auctions SET expiration_time = ?, dog_id = ?, start_price = ?, name = ?, completed = ? WHERE id = ? RETURNING *");) {
      preparedStatement.setTimestamp(1, Timestamp.from(auction.getExpirationTime()));
      preparedStatement.setInt(2, auction.getDogId());
      preparedStatement.setDouble(3, auction.getStartPrice());
      preparedStatement.setString(4, auction.getName());
      preparedStatement.setBoolean(5, auction.getCompleted());
      preparedStatement.setInt(6, auction.getId());
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();

      if (resultSet.next()) {
        updatedAuction = auctionFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return updatedAuction;
  }

  @Override
  public void delete(Auction auction) {
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("DELETE FROM auctions WHERE id = ?");) {
      preparedStatement.setInt(1, auction.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public Auction activeAuctionForDogId(Integer dogId) {
    Auction foundAuction = null;
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("SELECT * FROM auctions WHERE dog_id = ? AND completed = false");) {
      preparedStatement.setInt(1, dogId);
      preparedStatement.executeQuery();
      ResultSet resultSet = preparedStatement.getResultSet();

      if (resultSet.next()) {
        foundAuction = auctionFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return foundAuction;
  }

  private Auction auctionFromResultSet(ResultSet resultSet) throws SQLException {
    Integer id = resultSet.getInt("id");
    Instant expirationTime = resultSet.getTimestamp("expiration_time").toInstant();
    Integer dogId = resultSet.getInt("dog_id");
    Double startPrice = resultSet.getDouble("start_price");
    String name = resultSet.getString("name");
    Boolean completed = resultSet.getBoolean("completed");
    return new Auction(id, dogId, expirationTime, startPrice, name, completed);
  }


  public List<Auction> getUserAuctions(Integer userId) {
    List<Auction> userAuctions = new ArrayList<>();
    try (Connection connection = Database.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM auctions WHERE dog_id IN(SELECT dog_id FROM dogs WHERE owner_id = ?)");) {
      preparedStatement.setInt(1, userId);
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        userAuctions.add(auctionFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return userAuctions;
  }

  public List<Auction> getRunningAuctions() {
    List<Auction> runningAuctions = new ArrayList<>();
    try (Connection connection = Database.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM auctions WHERE completed = false");) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        runningAuctions.add(auctionFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return runningAuctions;
  }
}
