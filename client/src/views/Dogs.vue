<template>
  <div class="dogs">
    <h1>Dogs List</h1>
    <ul>
      <li v-for="dog in dogs" :key="dog.id">
        <DogCard :dog="dog" />
      </li>
    </ul>
  </div>
</template>

<script>
import DogCard from "@/components/DogCard.vue";

export default {
  name: "dogs",
  components: {
    DogCard
  },
  data() {
    return {
      dogs: []
    };
  },
  mounted() {
    this.$api.get("/dogs").then(res => (this.dogs = res.data));
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
  margin: 0 10px;
}
</style>
