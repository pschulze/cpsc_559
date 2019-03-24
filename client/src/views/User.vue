<template>
  <div class="container">
    <h1>{{ user.username }}</h1>
    <h3>{{ user.id }}</h3>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

import DogCard from "@/components/DogCard.vue";

export default {
  name: "user",
  components: {
    DogCard
  },
  computed: {
    ...mapGetters({
      userById: "users/byId"
    }),
    user() {
      return this.userById(this.$route.params.id);
    }
  }
};
</script>
