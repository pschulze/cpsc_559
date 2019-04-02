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
    <button
      v-if="loggedin && dog.ownerId === userId"
      type="button"
      class="btn btn-primary"
      @click.prevent="$refs.editAuctionModal.showModal"
    >
      Edit Auction
    </button>

    <portal to="modals">
      <Modal
        ref="editAuctionModal"
        title="Edit Auction"
        @hide="$refs.editAuctionForm.reset()"
      >
        <AuctionForm
          :auction="auction"
          ref="editAuctionForm"
          @submitSuccess="$refs.editAuctionModal.hideModal()"
        />
      </Modal>
    </portal>

    <Modal
      ref="editAuctionModal"
      title="Edit Auction"
      @hide="$refs.editAuctionForm.reset()"
    >
      <AuctionForm
        :auction="auction"
        ref="editAuctionForm"
        @submitSuccess="$refs.editAuctionModal.hideModal()"
      />
    </Modal>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";

import BidForm from "@/components/BidForm.vue";

import Modal from "@/components/Modal.vue";
import AuctionForm from "@/components/AuctionForm.vue";

export default {
  name: "auction",
  components: {
    BidForm,
    Modal,
    AuctionForm
  },
  computed: {
    ...mapState(["userId"]),
    ...mapGetters({
      auctionById: "auctions/active/byId",
      dogById: "dogs/byId",
      loggedin: "loggedin"
    }),
    auction() {
      return this.auctionById(Number(this.$route.params.id));
    },
    dog() {
      return this.dogById(this.auction.dogId);
    },
    currentAmount() {
      if (this.auction.highestBid && this.auction.highestBid.amount)
        return this.auction.highestBid.amount;
      else return this.auction.startPrice;
    }
  }
};
</script>
