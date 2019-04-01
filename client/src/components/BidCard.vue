<template>
    <div class="card" style="width: 18rem;">
      <div class="card-body">
        <h5 class="card-title">{{ auction.name }}</h5>
        <h6 class="card-subtitle mb-2 text-muted">Bid ID: {{ bid.id }}</h6>
        <h6 class="card-subtitle mb-2 text-muted">Dog Name: {{ dog.name }}</h6>
        <h6 class="card-subtitle mb-2 text-muted">Bid Amount: ${{ bid.amount }}</h6>
      </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import head from "lodash/head";
import VueContentLoading from "vue-content-loading";

export default {
  name: "BidCard",
  components: {
    VueContentLoading
  },
  props: {
    bid: Object,
    outlineonly: Boolean,
    sm: Boolean
  },
  computed: {
    ...mapGetters({
        auctionById: "auctions/byId",
        dogById: "dogs/byId"
    }),
    auction() {
      return this.auctionById(this.bid.auctionId);
    },
    dog() {
      return this.dogById(this.auction.dogId);
    }
  }
};
</script>

<style>
svg.bidCardBody {
  width: 124px;
  vertical-align: top;
}
</style>
