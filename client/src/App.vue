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
import { mapState } from "vuex";

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
  data() {
    return {
      auctionsPolling: null,
      dogsPolling: null,
      usersPolling: null
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
    startPollingAPI() {
      this.$store.dispatch("auctions/fetchAll");
      this.auctionsPolling = setInterval(
        function() {
          this.$store.dispatch("auctions/fetchAll");
        }.bind(this),
        3000
      );

      this.$store.dispatch("dogs/fetchAll");
      this.dogsPolling = setInterval(
        function() {
          this.$store.dispatch("dogs/fetchAll");
        }.bind(this),
        15000
      );

      this.$store.dispatch("users/fetchAll");
      this.usersPolling = setInterval(
        function() {
          this.$store.dispatch("users/fetchAll");
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
