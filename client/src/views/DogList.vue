<template>
  <div class="container">
    <h1>Dogs List</h1>
    <ul class="row flex-wrap justify-content-around justify-content-md-start">
      <template v-if="loading">
        <li class="col-auto">
          <DogCard class="my-2" :outlineonly="true" />
        </li>
      </template>
      <template v-else>
        <li class="col-auto" v-for="dog in dogs" :key="dog.id">
          <DogCard class="my-2" :dog="dog" />
        </li>
      </template>
    </ul>
  </div>
</template>

<script>
import DogCard from "@/components/DogCard.vue";

export default {
  name: "doglist",
  components: {
    DogCard
  },
  data() {
    return {
      loading: false,
      dogs: []
    };
  },
  mounted() {
    this.loading = true;
    this.$api
      .get("/dogs")
      .then(res => (this.dogs = res.data))
      .finally(() => {
        this.loading = false;
      });
  }
};
</script>

<style scoped>
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
}
</style>
