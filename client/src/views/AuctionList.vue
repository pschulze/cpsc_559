<template>
  <div class="container">
    <h1>Auction List</h1>
    <CardList :items="auctions">
      <template v-slot:loading>
        <AuctionCard :outlineonly="true" />
      </template>
      <template v-slot:default="{ item }">
        <AuctionCard :auction="item" />
      </template>
    </CardList>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

import CardList from "@/components/CardList.vue";
import AuctionCard from "@/components/AuctionCard.vue";

export default {
  name: "auctionlist",
  components: {
    CardList,
    AuctionCard
  },
  computed: {
    ...mapGetters({
      auctions: "auctions/active/all"
    })
  }
};
</script>
