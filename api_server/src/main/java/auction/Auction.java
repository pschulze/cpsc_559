package auction;

import dog.Dog;

public class Auction {

    private String hostId;
    private String dogId;
    private int duration;
    private int highestBid;
    private String highestBidderId;

    public String getHostId() { return hostId; }

    public String getDogId() { return dogId; }

    public int getDuration() { return duration; }

    public int getHighestBid() { return highestBid; }

    public String getHighestBidderId() { return highestBidderId; }

    public void setHostId(String hostId) { this.hostId = hostId; }

    public void setDogId(String dogId) { this.dogId = dogId; }

    public void setDuration(int duration) { this.duration = duration; }

    public void setHighestBid(int highestBid) { this.highestBid = highestBid; }

    public void setHighestBidderId(String highestBidderId) { this.highestBidderId = highestBidderId; }

    Auction() {}

    Auction(String hostId, String dogId, int duration) {
        this.hostId = hostId;
        this.dogId = dogId;
        this.duration = duration;
    }

}
