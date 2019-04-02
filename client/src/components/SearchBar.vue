<template>
  <div>
    <template v-if="dogsPage()">
      <form @submit.prevent.stop="searchDog">
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
          <button type="submit" class="btn btn-primary">
            {{ submitLabel }}
          </button>
        </div>
      </form>
    </template>
    <template v-else-if="auctionsPage()">
      <form @submit.prevent.stop="searchAuction">
        <div class="input-group">
          <input
            type="text"
            class="form-control"
            aria-label="Text input"
            placeholder="Search Auction Name..."
            id="searchAuctionName"
            v-model="AuctionName"
          />
          <button type="submit" class="btn btn-primary">
            {{ submitLabel }}
          </button>
        </div>
      </form>
    </template>
  </div>
</template>

<script>
export default {
  name: "SearchBar",
  props: {
    submitLabel: {
      type: String,
      default: "Search"
    }
  },
  data() {
    return {
      DogName: null,
      DogBreed: null,
      AuctionName: null
    };
  },
  watch: {
    $route() {
      this.reset();
    }
  },
  methods: {
    reset() {
      this.DogName = null;
      this.DogBreed = null;
      this.AuctionName = null;
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
      if (!this.DogName && !this.DogBreed)
        return this.$emit("searchResult", null);
      this.$api.Dogs.search({
        name: this.DogName,
        breed: this.DogBreed
      })
        .then(dogs => {
          this.$emit("searchResult", dogs);
        })
        .catch(() => {});
    },
    searchAuction() {
      if (!this.AuctionName) return this.$emit("searchResult", null);
      this.$api.Auctions.search({ name: this.AuctionName })
        .then(auctions => {
          this.$emit("searchResult", auctions);
        })
        .catch(() => {});
    }
  }
};
</script>
