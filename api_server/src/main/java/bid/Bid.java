package bid;

/**
 * Bid
 */
public class Bid {

  private Integer id;
  private Integer auctionId;
  private Integer bidderId;
  private Double amount;


  public Bid(Integer auctionId, Double amount) {
    this.auctionId = auctionId;

    // Truncate amount to 2 decimal places
    this.amount = Math.floor(amount * 100) / 100;
  }

  public Bid(Integer id, Integer auctionId, Double amount) {
    this.id = id;
    this.auctionId = auctionId;

    // Truncate amount to 2 decimal places
    this.amount = Math.floor(amount * 100) / 100;
  }

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the auctionId
   */
  public Integer getAuctionId() {
    return auctionId;
  }

  /**
   * @param auctionId the auctionId to set
   */
  public void setAuctionId(Integer auctionId) {
    this.auctionId = auctionId;
  }

  /**
   * @return the amount
   */
  public Double getAmount() {
    return amount;
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount(Double amount) {
    this.amount = Math.floor(amount * 100) / 100;
  }

    /**
   * @return the bidderId
   */
  public Integer getBidderId() {
    return bidderId;
  }

  /**
   * @param bidderId the bidderId to set
   */
  public void setBidderId(Integer bidderId) {
    this.bidderId = bidderId;
  }

  public boolean isValid() {
    boolean valid = true;
    if (this.amount == null || this.amount <= 0) {
      valid = false;
    }

    if (this.auctionId == null) {
      valid = false;
    }

    if (this.bidderId == null) {
      valid = false;
    }

    return valid;
  }
}
