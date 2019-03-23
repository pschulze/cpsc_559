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
          <NavbarAccount />
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
// @ is an alias to /src
import NavbarAccount from "@/components/NavbarAccount.vue";
import NavbarLink from "@/components/NavbarLink.vue";

export default {
  components: {
    NavbarAccount,
    NavbarLink
  }
};
</script>

<style></style>
