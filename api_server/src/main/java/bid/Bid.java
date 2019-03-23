package bid;

import java.util.ArrayList;
import java.util.List;

/**
 * Bid
 */
public class Bid {

  private Integer id;
  private Integer auctionId;
  private Integer bidderId;
  private Double amount;


  public Bid(Integer auctionId, Integer bidderId, Double amount) {
    this.auctionId = auctionId;
    this.bidderId = bidderId;

    // Truncate amount to 2 decimal places
    this.amount = Math.floor(amount * 100) / 100;
  }

  public Bid(Integer id, Integer auctionId, Integer bidderId, Double amount) {
    this.id = id;
    this.auctionId = auctionId;
    this.bidderId = bidderId;

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

  public List<String> validate() {
    List<String> errors = new ArrayList<>();
    if (this.amount == null)
      errors.add("amount is null");
    else if (this.amount <= 0)
      errors.add("amount is invalid");

    if (this.auctionId == null)
      errors.add("auctionId is null");

    if (this.bidderId == null)
      errors.add("bidderId is null");

    return errors;
  }
}
