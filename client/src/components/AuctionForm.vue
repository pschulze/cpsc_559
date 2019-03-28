<template>
  <form ref="form" @submit="onSubmit" novalidate>
    <div class="form-group">
      <label for="auctionFormName">Name</label>
      <input
        type="text"
        class="form-control"
        id="auctionFormName"
        placeholder="Enter Name"
        v-model="name"
        required
      />
    </div>
    <div class="form-group">
      <label for="auctionFormDog">Dog</label>
      <DogPicker class="form-control" v-model="dogId" required />
    </div>
    <div class="form-group">
      <label for="auctionFormStartPrice">Start Price</label>
      <div class="input-group mb-2">
        <div class="input-group-prepend">
          <div class="input-group-text">$</div>
        </div>
        <input
          type="number"
          class="form-control"
          id="auctionFormStartPrice"
          value="0"
          min="0"
          step="1"
          v-model="startPrice"
          required
        />
      </div>
    </div>
    <div class="form-group">
      <label for="auctionFormEndTime">End Time</label>
      <VueCtkDateTimePicker
        :min-date="currentDate()"
        format="YYYY-MM-DDTHH:mm:ssZ"
        :value="expirationTime"
        @input="saveExpirationTimeUTC"
        noValueToCustomElem
      >
        <input
          id="auctionFormEndTime"
          placeholder="Select date &amp; time"
          type="text"
          @focus="setDateReadonly(true)"
          @blur="setDateReadonly(false)"
          class="form-control"
          :value="formattedExpirationTime"
          required
        />
      </VueCtkDateTimePicker>
    </div>
    <button type="submit" class="btn btn-primary">
      {{ submitLabel }}
    </button>
  </form>
</template>

<script>
import Vue from "vue";
import Moment from "moment";

import DogPicker from "@/components/DogPicker.vue";
import VueCtkDateTimePicker from "vue-ctk-date-time-picker";
import "vue-ctk-date-time-picker/dist/vue-ctk-date-time-picker.css";

export default {
  name: "AuctionForm",
  components: {
    DogPicker,
    VueCtkDateTimePicker
  },
  props: {
    auction: {
      id: Number,
      dogId: Number,
      expirationTime: Number,
      startPrice: Number,
      name: String,
      completed: Boolean
    },
    dog: {
      id: Number
    },
    submitLabel: {
      type: String,
      default: "Save"
    }
  },
  data() {
    return {
      id: this.auction ? Vue.util.extend({}, this.auction.id) : null,
      dogId: this.auction
        ? Vue.util.extend({}, this.auction.dogId)
        : this.dog
        ? Vue.util.extend({}, this.dog.id)
        : null,
      expirationTime: this.auction
        ? Vue.util.extend({}, this.auction.expirationTime)
        : null,
      startPrice: this.auction
        ? Vue.util.extend({}, this.auction.startPrice)
        : 0,
      name: this.auction ? Vue.util.extend({}, this.auction.name) : null,
      completed: this.auction
        ? Vue.util.extend({}, this.auction.completed)
        : null
    };
  },
  computed: {
    formattedExpirationTime() {
      return this.expirationTime
        ? Moment(this.expirationTime).format("llll")
        : null;
    }
  },
  methods: {
    reset() {
      this.id = this.auction ? Vue.util.extend({}, this.auction.id) : null;
      this.dogId = this.auction
        ? Vue.util.extend({}, this.auction.dogId)
        : this.dog
        ? Vue.util.extend({}, this.dog.id)
        : null;
      this.expirationTime = this.auction
        ? Vue.util.extend({}, this.auction.expirationTime)
        : null;
      this.startPrice = this.auction
        ? Vue.util.extend({}, this.auction.startPrice)
        : 0;
      this.name = this.auction ? Vue.util.extend({}, this.auction.name) : null;
      this.completed = this.auction
        ? Vue.util.extend({}, this.auction.completed)
        : null;
      this.$refs.form.classList.remove("was-validated");
    },
    setDateReadonly(bool) {
      // jQuery loaded from cdn in browser for Bootstrap
      // eslint-disable-next-line no-undef
      $("#auctionFormEndTime").prop("readonly", bool);
    },
    currentDate() {
      return Moment().format("YYYY-MM-DD");
    },
    saveExpirationTimeUTC(value) {
      this.expirationTime = Moment.parseZone(value)
        .utc()
        .format();
    },
    checkForm(e) {
      // Do bootstrap's form validation
      if (this.$refs.form.checkValidity() === false) {
        this.$refs.form.classList.add("was-validated");
        e.preventDefault();
        e.stopPropagation();
        return false;
      }
      return true;
    },
    createAuction() {
      return this.$store.dispatch("auctions/create", {
        dogId: this.dogId,
        expirationTime: this.expirationTime,
        startPrice: this.startPrice,
        name: this.name,
        completed: this.completed
      });
    },
    updateAuction() {
      return this.$store.dispatch("auctions/update", this.id, {
        expirationTime: this.expirationTime,
        startPrice: this.startPrice,
        name: this.name,
        completed: this.completed
      });
    },
    onSubmit(e) {
      if (!this.checkForm(e)) return;
      let saveAction;
      if (this.auction && this.auction.id) {
        saveAction = this.updateAuction();
      } else {
        saveAction = this.createAuction();
      }
      saveAction.then(() => {
        this.$emit("sumbitSuccess");
        this.reset();
      });
    }
  }
};
</script>

<style>
#auctionFormEndTime {
  background-color: inherit;
  cursor: pointer;
}
</style>
