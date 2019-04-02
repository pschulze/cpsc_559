<template>
  <div class="card auctionCard">
    <template v-if="sm">
      <div v-if="outlineonly" class="card-body">
        <vue-content-loading :width="124" :height="60" class="auctionCardBody">
          <rect x="0" y="0" rx="4" ry="4" width="80" height="24" />
          <rect x="0" y="36" rx="4" ry="4" width="124" height="21" />
        </vue-content-loading>
      </div>
      <div v-else class="card-body">
        <h5 class="card-title">{{ auction.name }}</h5>
        <router-link :to="{ name: 'auction', params: { id: auction.id } }"
          >More Information</router-link
        >
      </div>
    </template>
    <template v-else>
      <template v-if="outlineonly">
        <div class="card-body">
          <vue-content-loading
            :width="124"
            :height="81"
            class="auctionCardBody"
          >
            <rect x="0" y="0" rx="4" ry="4" width="80" height="24" />
            <rect x="0" y="32" rx="4" ry="4" width="70" height="19" />
            <rect x="0" y="63" rx="4" ry="4" width="124" height="21" />
          </vue-content-loading>
        </div>
      </template>
      <template v-else>
        <div class="card-body">
          <router-link :to="{ name: 'auction', params: { id: auction.id } }">
            <h5 class="card-title">{{ auction.name }}</h5>
          </router-link>
          <h6 class="card-subtitle mb-2 text-muted">${{ currentAmount }}</h6>
        </div>
        <div class="card-footer text-muted">{{ endtimeString }}</div>
      </template>
    </template>
  </div>
</template>

<script>
import Moment from "moment";
import VueContentLoading from "vue-content-loading";

export default {
  name: "AuctionCard",
  components: {
    VueContentLoading
  },
  props: {
    auction: Object,
    outlineonly: Boolean,
    sm: Boolean
  },
  computed: {
    currentAmount() {
      if (this.auction.highestBid && this.auction.highestBid.amount)
        return this.auction.highestBid.amount;
      else return this.auction.startPrice;
    },
    endtimeString() {
      if (!this.auction.expirationTime) return "Endtimes unavailable offline";
      const now = Moment();
      const endtime = Moment(this.auction.expirationTime);
      if (now.diff(endtime) > 0) return "Ended " + endtime.fromNow();
      else return "Ends " + endtime.fromNow();
    }
  }
};
</script>

<style>
.auctionCard {
  width: 265px;
}
.auctionCard a {
  color: inherit;
}
svg.auctionCardBody {
  width: 124px;
  vertical-align: top;
}
</style>
