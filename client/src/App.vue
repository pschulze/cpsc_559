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
            <router-link
              to="/"
              tag="li"
              class="nav-item"
              active-class="active"
              exact
            >
              <a class="nav-link">Home</a>
            </router-link>
            <router-link
              to="/about"
              tag="li"
              class="nav-item"
              active-class="active"
            >
              <a class="nav-link">About</a>
            </router-link>
            <router-link
              to="/dogs"
              tag="li"
              class="nav-item"
              active-class="active"
            >
              <a class="nav-link">Dogs</a>
            </router-link>
          </ul>
          <router-link class="btn btn-outline-primary my-2 my-sm-0" to="/login"
            >Login</router-link
          >
        </div>
      </nav>
    </header>

    <main>
      <router-view />
    </main>

    <footer class="text-muted">
      <div class="container">
        <div class="d-flex justify-content-center">
          <p>CPSC 559 - Group 2</p>
        </div>
      </div>
    </footer>
  </div>
</template>

<style></style>
