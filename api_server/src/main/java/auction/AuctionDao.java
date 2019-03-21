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
    try {
      Connection connection = Database.getConnection();
      PreparedStatement preparedStatement =
        connection.prepareStatement("SELECT * FROM auctions WHERE id = ?", ResultSet.TYPE_SCROLL_SENSITIVE);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      boolean found = resultSet.first();

      if (found) {
        foundAuction = auctionFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return foundAuction;
  }


  @Override
  public List<Auction> getAll() {
    List<Auction> allAuctions = new ArrayList<>();
    try {
      Connection connection = Database.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM auctions");
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
    try {
      Connection connection = Database.getConnection();
      PreparedStatement preparedStatement =
        connection.prepareStatement("INSERT INTO auctions (id, expiration_time, dog_id, start_price, completed) VALUES (?, ?, ?, ?, ?) RETURNING *");
      preparedStatement.setInt(1, auction.getId());
      preparedStatement.setTimestamp(2, Timestamp.from(auction.getExpirationTime()));
      preparedStatement.setInt(3, auction.getDogId());
      preparedStatement.setDouble(4, auction.getStartPrice());
      preparedStatement.setBoolean(5, auction.getCompleted());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getResultSet();
      Boolean saved = resultSet.first();

      if (saved) {
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
    try {
      Connection connection = Database.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE auctions SET expiration_date = ?, dog_id = ?, start_price = ?, completed = ? WHERE id = ? RETURNING *");
      preparedStatement.setTimestamp(1, Timestamp.from(auction.getExpirationTime()));
      preparedStatement.setInt(2, auction.getDogId());
      preparedStatement.setDouble(3, auction.getStartPrice());
      preparedStatement.setBoolean(4, auction.getCompleted());
      preparedStatement.setInt(5, auction.getId());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getResultSet();
      Boolean updated = resultSet.first();

      if (updated) {
        updatedAuction = auctionFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return updatedAuction;
  }

  @Override
  public void delete(Auction auction) {
    try {
      Connection connection = Database.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM auctions WHERE id = ?");
      preparedStatement.setInt(1, auction.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private Auction auctionFromResultSet(ResultSet resultSet) throws SQLException {
    Integer id = resultSet.getInt("id");
    Instant expirationTime = resultSet.getTime("expiration_time").toInstant();
    Integer dogId = resultSet.getInt("dog_id");
    Double startPrice = resultSet.getDouble("start_price");
    Boolean completed = resultSet.getBoolean("completed");
    return new Auction(id, dogId, expirationTime, startPrice, completed);
  }
}
