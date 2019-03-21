package bid;

import java.text.DecimalFormat;

/**
 * Bid
 */
public class Bid {

  private Integer id;
  private Integer auctionId;
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
    this.amount = amount;
  }
}
