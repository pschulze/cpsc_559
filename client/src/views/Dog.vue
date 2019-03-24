<template>
  <div class="container">
    <h1>{{ dog.name }}</h1>
    <h3>{{ dog.id }}</h3>
    <p>
      Owner:
      <router-link :to="{ name: 'user', params: { id: user.id } }">{{
        user.username
      }}</router-link>
    </p>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "dog",
  computed: {
    ...mapGetters({
      dogById: "dogs/byId",
      userById: "users/byId"
    }),
    dog() {
      return this.dogById(this.$route.params.id);
    },
    user() {
      return this.userById(this.dog.ownerId);
    }
  }
};
</script>
