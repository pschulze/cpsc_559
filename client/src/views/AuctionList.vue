<template>
  <div class="container">
    <h1>Auction List</h1>
    <CardList :loading="loading" :items="auctions">
      <template v-slot:loading>
        <AuctionCard :outlineonly="true" />
      </template>
      <template v-slot:default="{ item }">
        <AuctionCard :auction="item" />
      </template>
    </CardList>
    <button @click="loading = !loading" />
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
  data() {
    return {
      loading: false
    };
  },
  computed: {
    ...mapGetters({
      auctions: "auctions/auctions"
    })
  }
};
</script>
