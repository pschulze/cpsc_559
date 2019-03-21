package bid;

import io.javalin.Handler;

/**
 * BidController
 */
public class BidController {

  private static BidDao bidDao = new BidDao();

  public static Handler create = ctx -> {
    Bid newBid = ctx.validatedBodyAsClass(Bid.class).check(Bid::isValid).getOrThrow();

  }
}
