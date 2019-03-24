<template>
  <div class="container">
    <h1>{{ auction.name }}</h1>
    <h3>{{ auction.id }}</h3>
    <p>
      Dog:
      <router-link :to="{ name: 'dog', params: { id: dog.id } }">{{
        dog.name
      }}</router-link>
    </p>
    <p>
      Owner:
      <router-link :to="{ name: 'user', params: { id: user.id } }">{{
        user.username
      }}</router-link>
    </p>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "auction",
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
