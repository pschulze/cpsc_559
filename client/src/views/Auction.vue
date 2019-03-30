<template>
  <div class="container">
    <h1>{{ auction.name }}</h1>
    <h3>{{ auction.id }}</h3>
    <BidForm :auction="auction" />
    <p>
      Dog:
      <router-link :to="{ name: 'dog', params: { id: dog.id } }">{{
        dog.name
      }}</router-link>
    </p>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

import BidForm from "@/components/BidForm.vue";

export default {
  name: "auction",
  components: {
    BidForm
  },
  computed: {
    ...mapGetters({
      auctionById: "auctions/byId",
      dogById: "dogs/byId"
    }),
    auction() {
      return this.auctionById(this.$route.params.id);
    },
    dog() {
      return this.dogById(this.auction.dogId);
    }
  }
};
</script>
