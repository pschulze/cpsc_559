<template>
  <div class="container">
    <h1>My Account</h1>
    <h3>My Dogs</h3>
    <CardList :items="dogs" v-slot="{ item }">
      <DogCard :dog="item" />
    </CardList>
    <button
      type="button"
      class="btn btn-primary"
      @click.prevent="$refs.addDogModal.showModal"
    >
      <slot name="button">
        Add Dog
      </slot>
    </button>
    <Modal ref="addDogModal" title="Add Dog" @hide="$refs.addDogForm.reset()">
      <DogForm ref="addDogForm" @submit="createDog" />
    </Modal>
    <hr />
    <h3>My Auctions</h3>
    <CardList :items="auctions" v-slot:default="{ item }">
      <AuctionCard :auction="item" />
    </CardList>
    <hr />
    <h3>My Bids</h3>
    <p>TODO</p>
  </div>
</template>

<script>
import { mapState, mapGetters } from "vuex";
import flatMap from "lodash/flatMap";

import CardList from "@/components/CardList.vue";
import DogCard from "@/components/DogCard.vue";
import AuctionCard from "@/components/AuctionCard.vue";

import Modal from "@/components/Modal.vue";
import DogForm from "@/components/DogForm.vue";

export default {
  name: "account",
  components: {
    CardList,
    DogCard,
    AuctionCard,
    DogForm,
    Modal
  },
  computed: {
    ...mapState(["userId", "username"]),
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
  },
  methods: {
    createDog(dog) {
      console.log("a", dog);
      this.$refs.addDogModal.hideModal();
    }
  }
};
</script>
