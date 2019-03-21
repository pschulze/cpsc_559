package auction;

import java.time.Instant;

public class Auction {

    private Integer id;
    private Integer dogId;
    private Instant expirationTime;
    private Double startPrice;
    private Boolean completed = false;

    public Auction(Integer dogId, Instant expirationTime, Double startPrice, Boolean completed) {
        this.dogId = dogId;
        this.expirationTime = expirationTime;
        this.startPrice = startPrice;
        this.completed = completed;
    }

    public Auction(Integer id, Integer dogId, Instant expirationTime, Double startPrice, Boolean completed) {
        this.id = id;
        this.dogId = dogId;
        this.expirationTime = expirationTime;
        this.startPrice = startPrice;
        this.completed = completed;
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
     * @return the dogId
     */
    public Integer getDogId() {
        return dogId;
    }

    /**
     * @param dogId the dogId to set
     */
    public void setDogId(Integer dogId) {
        this.dogId = dogId;
    }

    /**
     * @return the expirationTime
     */
    public Instant getExpirationTime() {
        return expirationTime;
    }

    /**
     * @param expirationTime the expirationTime to set
     */
    public void setExpirationTime(Instant expirationTime) {
        this.expirationTime = expirationTime;
    }

    /**
     * @return the startPrice
     */
    public Double getStartPrice() {
        return startPrice;
    }

    /**
     * @param startPrice the startPrice to set
     */
    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    /**
     * @return the completed
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
