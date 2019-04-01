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
  </div>
</template>

<script>
import { mapState, mapGetters } from "vuex";

import { Auctions, Bids, Dogs, Users, setStore } from "@/api_polling";

// @ is an alias to /src
import NavbarAccount from "@/components/NavbarAccount.vue";
import NavbarStatus from "@/components/NavbarStatus.vue";
import NavbarLink from "@/components/NavbarLink.vue";

export default {
  components: {
    NavbarAccount,
    NavbarStatus,
    NavbarLink
  },
  computed: {
    ...mapState(["apiAvailable"]),
    ...mapGetters(["loggedin"])
  },
  watch: {
    apiAvailable(available) {
      if (available) this.startPollingAPI();
      else this.stopPollingAPI();
    },
    loggedin(loggedin) {
      if (loggedin) this.startPollingBids();
      else this.stopPollingBids();
    }
  },
  methods: {
    startPollingAPI() {
      Auctions.startPolling();
      Dogs.startPolling();
      Users.startPolling();
      if (this.loggedin) this.startPollingBids();
    },
    startPollingBids() {
      Bids.startPolling();
    },
    stopPollingAPI() {
      Auctions.stopPolling();
      Dogs.stopPolling();
      Users.stopPolling();
      if (this.loggedin) this.stopPollingBids();
    },
    stopPollingBids() {
      Bids.stopPolling();
    }
  },
  mounted() {
    setStore(this.$store);
    this.startPollingAPI();
  },
  beforeDestroy() {
    this.stopPollingAPI();
  }
};
</script>

<style></style>
