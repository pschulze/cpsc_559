<template>
  <div class="dogs">
    <h1>Dogs List</h1>
    <ul>
      <li v-if="loading">
        <DogCardLoading/>
      </li>
      <li v-else v-for="dog in dogs" :key="dog.id">
        <DogCard :dog="dog" />
      </li>
    </ul>
  </div>
</template>

<script>
import DogCard from "@/components/DogCard.vue";
import DogCardLoading from "@/components/DogCardLoading.vue";

export default {
  name: "dogs",
  components: {
    DogCard,
    DogCardLoading
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
