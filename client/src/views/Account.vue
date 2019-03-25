<template>
  <div class="container">
    <h1>My Account</h1>
    <h3>My Dogs</h3>
    <CardList :items="dogs" v-slot="{ item }">
      <DogCard :dog="item" />
    </CardList>
    <h3>My Auctions</h3>
    <CardList :items="auctions" v-slot:default="{ item }">
      <AuctionCard :auction="item" />
    </CardList>
  </div>
</template>

<script>
import { mapState, mapGetters } from "vuex";
import flatMap from "lodash/flatMap";

import CardList from "@/components/CardList.vue";
import DogCard from "@/components/DogCard.vue";
import AuctionCard from "@/components/AuctionCard.vue";

export default {
  name: "account",
  components: {
    CardList,
    DogCard,
    AuctionCard
  },
  computed: {
    ...mapState(["userId", "username"] ),
    ...mapGetters({
      userById: "users/byId",
      dogsByOwner: "dogs/byOwner",
      auctionsByDog: "auctions/byDog"
    }),
    user() {
      return this.userById(this.userId);
    },
    dogs() {
      return this.dogsByOwner(this.userId);
    },
    auctions() {
      return flatMap(this.dogs, dog => this.auctionsByDog(dog.id));
    }
  }
};
</script>
