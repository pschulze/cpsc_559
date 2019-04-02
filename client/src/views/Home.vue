<template>
  <div class="container">
    <div class="d-flex p-3 mx-auto flex-column text-center">
      <div class="inner cover">
        <img alt="Vue logo" src="../assets/256px-Black_Paw.svg.png" />
        <h1 class="display-4">The Puppy Project</h1>
        <p class="lead">
          An online auction site for dogs.
        </p>
        <p>
          <router-link class="btn btn-primary my-2 my-sm-0" to="/auctions"
            >View Auctions</router-link
          >
          &nbsp;
          <router-link class="btn btn-secondary my-2 my-sm-0" to="/dogs"
            >View Dogs</router-link
          >
        </p>
      </div>
    </div>
  </div>
</template>
