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
    <UserCard :user="user" sm />
  </div>
</template>

<script>
import { mapGetters } from "vuex";

import UserCard from "@/components/UserCard.vue";
import BidForm from "@/components/BidForm.vue";

export default {
  name: "auction",
  components: {
    UserCard,
    BidForm
  },
  computed: {
    ...mapGetters({
      auctionById: "auctions/byId",
      dogById: "dogs/byId",
      userById: "users/byId"
    }),
    auction() {
      return this.auctionById(this.$route.params.id);
    },
    dog() {
      return this.dogById(this.auction.dogId);
    },
    user() {
      return this.userById(this.dog.ownerId);
    }
  }
};
</script>
