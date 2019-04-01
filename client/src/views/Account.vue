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
      Add Dog
    </button>
    <portal to="modals">
      <Modal ref="addDogModal" title="Add Dog" @hide="$refs.addDogForm.reset()">
        <DogForm
          ref="addDogForm"
          @sumbitSuccess="$refs.addDogModal.hideModal()"
        />
      </Modal>
    </portal>
    <hr />
    <h3>My Auctions</h3>
    <CardList :items="auctions" v-slot:default="{ item }">
      <AuctionCard :auction="item" />
    </CardList>
    <button
      type="button"
      class="btn btn-primary"
      @click.prevent="$refs.addAuctionModal.showModal"
    >
      Add Auction
    </button>
    <portal to="modals">
      <Modal
        ref="addAuctionModal"
        title="Add Auction"
        @hide="$refs.addAuctionForm.reset()"
      >
        <AuctionForm
          ref="addAuctionForm"
          @sumbitSuccess="$refs.addAuctionModal.hideModal()"
        />
      </Modal>
    </portal>
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
import AuctionForm from "@/components/AuctionForm.vue";

export default {
  name: "account",
  components: {
    CardList,
    DogCard,
    AuctionCard,
    Modal,
    DogForm,
    AuctionForm
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
  }
};
</script>
