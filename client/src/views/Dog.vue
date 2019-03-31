<template>
  <div class="container">
    <h1>{{ dog.name }}</h1>
    <img :src="dog.imageUrl">
    <h4>ID: {{ dog.id }}</h4>
    <h4>Age: {{ dog.age }}</h4>
    <h4>Breed: {{ dog.breed }}</h4>
    <AuctionCard v-if="auction" :auction="auction" />
    <UserCard :user="owner" sm />
    <button v-if="loggedin && dog.ownerId === userId"
      type="button"
      class="btn btn-primary"
      @click.prevent="$refs.editDogModal.showModal"
    >
      Edit Dog
    </button>
    <Modal ref="editDogModal" title="Edit Dog" @hide="$refs.editDogForm.reset()">
      <DogForm :dog="dog"
        ref="editDogForm"
        @submitSuccess="$refs.editDogModal.hideModal()"
      />
    </Modal>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import head from "lodash/head";

import AuctionCard from "@/components/AuctionCard.vue";
import UserCard from "@/components/UserCard.vue";

import Modal from "@/components/Modal.vue";
import DogForm from "@/components/DogForm.vue";

export default {
  name: "dog",
  components: {
    AuctionCard,
    UserCard,
    Modal,
    DogForm
  },
  computed: {
    ...mapState(["userId"]),
    ...mapGetters({
      dogById: "dogs/byId",
      userById: "users/byId",
      auctionsByDog: "auctions/byDog",
      loggedin: "loggedin"
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
