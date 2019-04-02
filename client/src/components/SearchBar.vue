<template>
  <form>
    <template v-if="dogsPage()">
      <div class="input-group">
        <input
          type="text"
          class="form-control"
          aria-label="Text input"
          placeholder="Search Dog Name..."
          id="searchDogName"
          v-model="DogName"
        />
        <div class="input-group-append">
          <input
            type="text"
            class="form-control"
            aria-label="Text input"
            placeholder="Search Dog Breed..."
            id="searchDogBreed"
            v-model="DogBreed"
          />
        </div>
        <button type="submit" class="btn btn-primary" v-on:click="searchDog()">
          {{ submitLabel }}
        </button>
      </div>
    </template>
    <template v-else-if="auctionsPage()">
      <div class="input-group">
        <input
          type="text"
          class="form-control"
          aria-label="Text input"
          placeholder="Search Auction Name..."
          id="searchAuctionName"
          v-model="AuctionName"
        />
        <button
          type="submit"
          class="btn btn-primary"
          v-on:click="searchAuction()"
        >
          {{ submitLabel }}
        </button>
      </div>
    </template>
  </form>
</template>

<script>
export default {
  name: "SearchBar",
  props: {
    dog: {
      DogName: String,
      DogBreed: String
    },
    auction: {
      AuctionName: String
    },
    submitLabel: {
      type: String,
      default: "Search"
    }
  },
  data() {
    return {
      DogName: this.dog ? this.dog.DogName : null,
      DogBreed: this.dog ? this.dog.DogBreed : null,
      AuctionName: this.auction ? this.auction.AuctionName : null
    };
  },
  methods: {
    reset() {
      this.DogName = this.dog ? this.dog.DogName : null;
      this.DogBreed = this.dog ? this.dog.DogBreed : null;
      this.AuctionName = this.auction ? this.auction.AuctionName : null;
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
    dogsPage() {
      if (this.$route.path == "/dogs") {
        return true;
      } else {
        return false;
      }
    },
    auctionsPage() {
      if (this.$route.path == "/auctions") {
        return true;
      } else {
        return false;
      }
    },
    searchDog() {
      return this.$api.Dogs.search({
        name: this.DogName,
        breed: this.DogBreed
      }).then(dogs => {
        this.$emit("searchResult", dogs);
        this.reset();
      });
    },
    searchAuction() {
      return this.$api.Auctions.search({ name: this.AuctionName }).then(
        auctions => {
          this.$emit("searchResult", auctions);
          this.reset();
        }
      );
    }
  }
};
</script>
