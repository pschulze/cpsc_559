<template>
  <div class="container">
    <h1>{{ dog.name }}</h1>
    <h3>{{ dog.id }}</h3>
    <AuctionCard v-if="auction" :auction="auction" />
    <UserCard :user="owner" sm />
    <button
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
import { mapGetters } from "vuex";
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
