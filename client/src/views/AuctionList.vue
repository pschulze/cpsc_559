<template>
  <div class="container">
    <h1>Auction List</h1>
    <CardList :loading="loading" :items="auctions">
      <template v-slot:loading>
        <DogCard :outlineonly="true" />
      </template>
      <template v-slot:default="{ item }">
        <DogCard :dog="item" />
      </template>
    </CardList>
    <button @click="loading = !loading" />
  </div>
</template>

<script>
import { mapGetters } from "vuex";

import CardList from "@/components/CardList.vue";
import DogCard from "@/components/DogCard.vue";

export default {
  name: "auctionlist",
  components: {
    CardList,
    DogCard
  },
  data() {
    return {
      loading: false,
    };
  },
  computed: {
    ...mapGetters({
      auctions: "auctions/auctions"
    })
  },
  mounted() {
    this.loading = true;
    this.$store.dispatch("auctions/fetchAll").finally(() => {
      this.loading = false;
    });
  }
};
</script>
