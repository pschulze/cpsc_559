<template>
  <div class="card" style="width: 18rem;">
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
          <h5 class="card-title">{{ auction.name }}</h5>
          <h6 class="card-subtitle mb-2 text-muted">${{ currentAmount }}</h6>
          <router-link :to="{ name: 'auction', params: { id: auction.id } }"
            >More Information</router-link
          >
        </div>
        <div class="card-footer text-muted">{{ auction.expirationTime }}</div>
      </template>
    </template>
  </div>
</template>

<script>
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
    }
  }
};
</script>

<style>
svg.auctionCardBody {
  width: 124px;
  vertical-align: top;
}
</style>
