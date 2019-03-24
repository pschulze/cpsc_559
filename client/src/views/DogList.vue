<template>
  <div class="container">
    <h1>Dogs List</h1>
    <CardList :loading="loading" :items="dogs">
      <template v-slot:loading>
        <DogCard :outlineonly="true" />
      </template>
      <template v-slot:default="{ item }">
        <DogCard :dog="item" />
      </template>
    </CardList>
    <button @click="loading = !loading" />
  </div>
</template>

<script>
import { mapGetters } from "vuex";

import CardList from "@/components/CardList.vue";
import DogCard from "@/components/DogCard.vue";

export default {
  name: "doglist",
  components: {
    CardList,
    DogCard
  },
  data() {
    return {
      loading: false
    };
  },
  computed: {
    ...mapGetters({
      dogs: "dogs/all"
    })
  }
};
</script>
