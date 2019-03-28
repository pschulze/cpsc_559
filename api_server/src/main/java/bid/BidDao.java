package bid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import auction.Auction;
import data.Dao;
import data.Database;
import user.User;

/**
 * BidDao
 */
public class BidDao implements Dao<Bid, Integer> {

  @Override
  public Bid get(Integer id) {
    Bid foundBid = null;
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("SELECT * FROM bids WHERE id = ?");) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      boolean found = resultSet.first();

      if (found) {
        foundBid = bidFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return foundBid;
  }

  @Override
  public List<Bid> getAll() {
    List<Bid> allBids = new ArrayList<>();
    try (Connection connection = Database.getConnection() ;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bids");) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        allBids.add(bidFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return allBids;
  }

  @Override
  public Bid save(Bid bid) {
    Bid savedBid = null;

    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("INSERT INTO bids (auction_id, bidder_id, amount) VALUES (?, ?, ?) RETURNING *");) {
      preparedStatement.setInt(1, bid.getAuctionId());
      preparedStatement.setInt(2, bid.getBidderId());
      preparedStatement.setDouble(3, bid.getAmount());
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();

      if (resultSet.next()) {
        savedBid = bidFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return savedBid;
  }

  @Override
  public Bid update(Bid bid) {
    Bid updatedBid = null;

    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("UPDATE bids SET auction_id = ?, bidder_id = ?, amount = ? WHERE id = ? RETURNING *");) {
      preparedStatement.setInt(1, bid.getAuctionId());
      preparedStatement.setInt(2, bid.getBidderId());
      preparedStatement.setDouble(2, bid.getAmount());
      preparedStatement.setInt(3, bid.getId());
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();

      if (resultSet.next()) {
        updatedBid = bidFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage() );
    }
    return updatedBid;
  }

  @Override
  public void delete(Bid bid) {
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("DELETE FROM bids WHERE id = ?");) {
      preparedStatement.setInt(1, bid.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public List<Bid> bidsForAuction(Auction auction) {
    List<Bid> foundBids = new ArrayList<>();
    try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement =
          connection.prepareStatement("SELECT * FROM bids WHERE auction_id = ?");) {
      preparedStatement.setInt(1, auction.getId());
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        foundBids.add(bidFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return foundBids;
  }

  private Bid bidFromResultSet(ResultSet resultSet) throws SQLException {
    Integer id = resultSet.getInt("id");
    Integer auctionId = resultSet.getInt("auction_id");
    Integer bidderId = resultSet.getInt("bidder_id");
    Double amount = resultSet.getDouble("amount");
    return new Bid(id, auctionId, bidderId, amount);
  }

  public List<Bid> bidsForUser(User user) {
    List<Bid> foundBids = new ArrayList<>();
    try (Connection connection = Database.getConnection();
         PreparedStatement preparedStatement =
                 connection.prepareStatement("SELECT * FROM bids WHERE bidder_id = ?");) {
      preparedStatement.setInt(1, user.getId());
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        foundBids.add(bidFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return foundBids;
  }
}
