package bid;

import java.util.HashMap;
import java.util.Map;

import auction.Auction;
import auction.AuctionDao;
import io.javalin.Handler;

/**
 * BidController
 */
public class BidController {
  private static BidDao bidDao = new BidDao();
  private static AuctionDao auctionDao = new AuctionDao();

  public static Handler getBidsForAuction = ctx -> {
    Integer id = Integer.parseInt(ctx.pathParam(":id"));
    Auction auction = auctionDao.get(id);
    if (auction == null) {
      Map<String, Object> message = new HashMap<>();
      message.put("status", 404);
      message.put("details", "auction not found for id: " + id.toString());
      ctx.status(404).json(message);
    } else {
      ctx.json(bidDao.bidsForAuction(auction));
    }
  };
}
