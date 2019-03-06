<template>
  <div class="container">
    <h1>Dogs List</h1>
    <ul>
      <li v-if="loading">
        <DogCard :outlineonly="true"/>
      </li>
      <template v-else>
        <li v-for="dog in dogs" :key="dog.id">
          <DogCard :dog="dog" />
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
    DogCard,
  },
  data() {
    return {
      loading: false,
      dogs: []
    };
  },
  mounted() {
    this.loading = true;
    this.$api.get("/dogs").then(res => (this.dogs = res.data)).finally(() => {this.loading = false;});
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
