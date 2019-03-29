<template>
  <div class="container">
    <h1>{{ dog.name }}</h1>
    <h4>ID: {{ dog.id }}</h4>
    <h4>Age: {{ dog.age }}</h4>
    <h4>Breed: {{ dog.breed }}</h4>
    <AuctionCard v-if="auction" :auction="auction" />
    <UserCard :user="user" sm />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import head from "lodash/head";

import AuctionCard from "@/components/AuctionCard.vue";
import UserCard from "@/components/UserCard.vue";

export default {
  name: "dog",
  components: {
    AuctionCard,
    UserCard
  },
  computed: {
    ...mapGetters({
      dogById: "dogs/byId",
      userById: "users/byId",
      auctionsByDog: "auctions/byDog"
    }),
    dog() {
      return this.dogById(this.$route.params.id);
    },
    user() {
      return this.userById(this.dog.ownerId);
    },
    auction() {
      return head(this.auctionsByDog(this.$route.params.id));
    }
  }
};
</script>
