<template>
  <div class="container">
    <h1>{{ dog.name }}</h1>
    <h3>{{ dog.id }}</h3>
    <AuctionCard v-if="auction" :auction="auction" />
    <UserCard :user="owner" sm />
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
    owner() {
      return this.userById(this.dog.ownerId);
    },
    auction() {
      return head(this.auctionsByDog(this.$route.params.id));
    }
  }
};
</script>
