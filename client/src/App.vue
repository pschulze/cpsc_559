<template>
  <div id="app">
    <header class="sticky-top">
      <nav class="navbar navbar-expand-md navbar-light bg-light">
        <router-link class="navbar-brand" to="/">The Puppy Project</router-link>
        <button
          class="navbar-toggler collapsed"
          type="button"
          data-toggle="collapse"
          data-target="#navbarCollapse"
          aria-controls="navbarCollapse"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="navbar-collapse collapse" id="navbarCollapse" style="">
          <ul class="navbar-nav mr-auto">
            <NavbarLink to="/" exact>Home</NavbarLink>
            <NavbarLink to="/auctions">Auctions</NavbarLink>
            <NavbarLink to="/dogs">Dogs</NavbarLink>
          </ul>
            <div style="margin: auto; width: 50%; ">
              <portal-target name="searchbar" />
            </div>
          <div class="d-flex">
            <NavbarStatus class="ml-3" />
            <NavbarAccount class="ml-3" />
          </div>
        </div>
      </nav>
    </header>

    <main>
      <router-view />
    </main>

    <footer class="text-muted">
      <div class="container">
        <div class="d-flex justify-content-center">
          <small>CPSC 559 - Group 2</small>
        </div>
      </div>
    </footer>

    <portal-target name="modals" multiple />
  </div>
</template>

<script>
import { mapState } from "vuex";

// @ is an alias to /src
import NavbarAccount from "@/components/NavbarAccount.vue";
import NavbarStatus from "@/components/NavbarStatus.vue";
import NavbarLink from "@/components/NavbarLink.vue";
import SearchBar from "@/components/SearchBar.vue";

export default {
  components: {
    NavbarAccount,
    NavbarStatus,
    NavbarLink,
    SearchBar
  },
  data() {
    return {
      auctionsPolling: null,
      dogsPolling: null,
      usersPolling: null,
      searchResult: null
    };
  },
  computed: {
    ...mapState(["apiAvailable"])
  },
  watch: {
    apiAvailable(available) {
      if (available) this.startPollingAPI();
      else this.stopPollingAPI();
    }
  },
  methods: {
    errorHandler(err) {
      switch (err.status) {
        case 418:
          // Internal error sending request
          // Ignore
          break;
        case 521:
        default:
          this.$store.dispatch("apiUnavailable");
      }
    },
    fetchAuctions() {
      return this.$store
        .dispatch("auctions/fetchAll")
        .catch(err => this.errorHandler(err));
    },
    fetchDogs() {
      return this.$store
        .dispatch("dogs/fetchAll")
        .catch(err => this.errorHandler(err));
    },
    fetchUsers() {
      return this.$store
        .dispatch("users/fetchAll")
        .catch(err => this.errorHandler(err));
    },
    startPollingAPI() {
      this.fetchAuctions();
      this.auctionsPolling = setInterval(
        function() {
          this.fetchAuctions();
        }.bind(this),
        3000
      );

      this.fetchDogs();
      this.dogsPolling = setInterval(
        function() {
          this.fetchDogs();
        }.bind(this),
        15000
      );

      this.fetchUsers();
      this.usersPolling = setInterval(
        function() {
          this.fetchUsers();
        }.bind(this),
        15000
      );
    },
    stopPollingAPI() {
      clearInterval(this.auctionsPolling);
      clearInterval(this.dogsPolling);
      clearInterval(this.usersPolling);
    }
  },
  mounted() {
    this.startPollingAPI();
  },
  beforeDestroy() {
    this.stopPollingAPI();
  }
};
</script>

<style></style>
