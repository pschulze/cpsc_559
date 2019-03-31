package auction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.time.Instant;

import bid.Bid;
import bid.BidDao;
import dog.Dog;
import dog.DogDao;
import io.javalin.Handler;
import user.UserDao;

public class AuctionController {

  private static AuctionDao auctionDao = new AuctionDao();
  private static BidDao bidDao = new BidDao();
  private static DogDao dogDao = new DogDao();

  public static Handler getAll = ctx -> ctx.json(auctionDao.getAll());

  public static Handler get = ctx -> {
    Integer id = Integer.parseInt(ctx.pathParam(":id"));
    Auction auction = auctionDao.get(id);
    if (auction == null) {
      Map<String, Object> message = new HashMap<>();
      message.put("status", 404);
      message.put("details", "auction not found for id: " + id.toString());
      ctx.status(404).json(message);
    } else {
      ctx.json(auction);
    }
  };

  public static Handler getOne = ctx -> {
    String name = ctx.queryParam("name");
    Auction auction = auctionDao.get(name);
    if (auction == null) {
      Map<String, Object> message = new HashMap<>();
      message.put("status", 404);
      message.put("details", "auction not found for name: " + name);
      ctx.status(404).json(message);
    } else {
      ctx.json(auction);
    }
  };

  /**
   * JSON Body format:
   * {
   *   "bidderId": 3,
   *   "amount": 12.34
   * }
   */
  public static Handler placeBid = ctx -> {
    Integer auctionId = Integer.parseInt(ctx.pathParam(":id"));
    Auction auction = auctionDao.get(auctionId);
    if (auction == null) {
      Map<String, Object> message = new HashMap<>();
      message.put("status", 404);
      message.put("details", "auction not found for id: " + auctionId.toString());
      ctx.status(404).json(message);
    } else {
      if (auction.getCompleted()) {
        Map<String, Object> message = new HashMap<>();
        message.put("status", 403);
        message.put("details", "auction with id: " + auctionId.toString() + " has already been completed");
        ctx.status(403).json(message);
        return;
      }

      Bid newBid = ctx.validatedBodyAsClass(Bid.class).getOrThrow();
      newBid.setAuctionId(auctionId);
      List<String> errors = newBid.validate();

      if (new UserDao().get(newBid.getBidderId()) == null) {
        errors.add("user not found for id: " + newBid.getBidderId().toString());
      }

      if (!errors.isEmpty()) {
        Map<String, Object> errorsMap = new HashMap<>();
        errorsMap.put("status", 400);
        errorsMap.put("errors", errors);
        ctx.status(400).json(errorsMap);
        return;
      }

      // get the current highest bid
      Bid highestBid = null;
      List<Bid> bids = bidDao.bidsForAuction(auction);
      for (Bid bid : bids) {
        if (highestBid == null) {
          highestBid = bid;
        } else if (bid.getAmount() > highestBid.getAmount()) {
          highestBid = bid;
        }
      }

      // Place the bid if it's higher than the current bid, otherwise give an error
      if (highestBid == null || newBid.getAmount() > highestBid.getAmount()) {
        Bid placedBid = bidDao.save(newBid);

        // Make sure this is the only bid at the given price
        bids = bidDao.bidsForAuction(auction);
        List<Bid> equalValueBids = new ArrayList<>();

        for (Bid bid : bids) {
          if (bid.getAmount().equals(placedBid.getAmount())) {
            equalValueBids.add(bid);
          }
        }

        // There's at least one duplicate bid, if placedBid has a higher id than any of the other bids, delete itself
        if (equalValueBids.size() > 1) {
          for (Bid bid : equalValueBids) {
            if (bid.getId() > placedBid.getId()) {
              bidDao.delete(placedBid);

              errors.add("bid amount not higher than the current highest bid");
              Map<String, Object> errorsMap = new HashMap<>();
              errorsMap.put("status", 409);
              errorsMap.put("errors", errors);
              ctx.status(409).json(errorsMap);
              return;
            }
          }
        } else {
          ctx.json(placedBid);
        }

      } else {
        errors.add("bid amount not higher than the current highest bid");
        Map<String, Object> errorsMap = new HashMap<>();
        errorsMap.put("status", 409);
        errorsMap.put("errors", errors);
        ctx.status(409).json(errorsMap);
      }
    }
  };

  /**
   * JSON Body Syntax:
   * {
   *   "dogId": 1,
   *   "expirationTime": "2019-03-31T17:47:57Z",
   *   "startPrice": 10.0,
   *   "name": "my fabulous dog auction"
   * }
   *
   * Note: expirationTime is a UTC timestamp in seconds
   */
  public static Handler create = ctx -> {
    Auction newAuction = ctx.bodyAsClass(Auction.class);
    List<String> errors = newAuction.validate();

    if (!errors.isEmpty()) {
      Map<String, Object> errorsMap = new HashMap<>();
      errorsMap.put("status", 400);
      errorsMap.put("errors", errors);
      ctx.status(400).json(errorsMap);
      return;
    }

    // Check to see if there's already an active auction for that dog
    if (auctionDao.activeAuctionForDogId(newAuction.getDogId()) != null)
      errors.add("active auction already exists for dogId: " + newAuction.getDogId().toString());

    if (!errors.isEmpty()) {
      Map<String, Object> errorsMap = new HashMap<>();
      errorsMap.put("status", 409);
      errorsMap.put("errors", errors);
      ctx.status(409).json(errorsMap);
    } else {
      Auction savedAuction = auctionDao.save(newAuction);
      ctx.json(savedAuction);
    }
  };

  public static Handler update = ctx -> {
    Integer id = Integer.parseInt(ctx.pathParam(":id"));
    Auction auction = auctionDao.get(id);
    if (auction == null) {
      Map<String, Object> message = new HashMap<>();
      message.put("status", 404);
      message.put("details", "Auction not found for ID " + id.toString());
      ctx.status(404).json(message);
    } else {
      Auction updatedAuction = ctx.bodyAsClass(Auction.class);
      auction.update(updatedAuction);
      Auction newAuction = auctionDao.update(auction);
      ctx.json(newAuction);
    }
  };

  public static Handler getUserAuctions = ctx -> {
    Integer userId = Integer.parseInt(ctx.pathParam(":id"));
    List<Auction> auctions = auctionDao.getUserAuctions(userId);
    if (auctions.isEmpty()) {

    } else {
      ctx.json(auctions);
    }
  };

  public static void endAuctions() {
    List<Auction> auctions = auctionDao.getRunningAuctions();
    for (Auction auction : auctions) {
      if (auction.getExpirationTime().isBefore(Instant.now())) {

        auction.setCompleted(true);
        auctionDao.update(auction);

        Bid highestBid = null;
        List<Bid> bids = bidDao.bidsForAuction(auction);

        if (!bids.isEmpty()) {
          // get the current highest bid
          for (Bid bid : bids) {
            if (highestBid == null) {
              highestBid = bid;
            } else if (bid.getAmount() > highestBid.getAmount()) {
              highestBid = bid;
            }
          }

          // change the owner of the dog
          int dogId = auction.getDogId();
          int newOwnerId = highestBid.getBidderId();
          Dog dog = dogDao.get(dogId);
          dog.setOwnerId(newOwnerId);
          dogDao.update(dog);
        }
      }
    }
  }
}
