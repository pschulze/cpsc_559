package auction;

import java.util.ArrayList;
import java.util.List;

import bid.Bid;
import bid.BidDao;

public class AuctionController {

  private static AuctionDao auctionDao = new AuctionDao();
  private static BidDao bidDao = new BidDao();

  public static void endAuctions() {

  }

  public static void placeBid(Bid bidToPlace, Auction auction) {
    if (auction.getCompleted()) {
      // TODO return an error, auction is done
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
    if (bidToPlace.getAmount() > highestBid.getAmount()) {
      Bid placedBid = bidDao.save(bidToPlace);

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
            // TODO return an error
          }
        }
      } else {
        // TODO return a success message
      }

    } else {
      // Return an error
    }
  }
}
