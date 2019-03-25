<template>
  <div class="container">
    <h1>Dogs List</h1>
    <CardList :items="dogs">
      <template v-slot:loading>
        <DogCard :outlineonly="true" />
      </template>
      <template v-slot:default="{ item }">
        <DogCard :dog="item" />
      </template>
    </CardList>
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
  computed: {
    ...mapGetters({
      dogs: "dogs/all"
    })
  }
};
</script>
