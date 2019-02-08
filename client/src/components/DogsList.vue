<template>
  <div class="dogslist">
    <h1>Dogs List</h1>
    <ul>
      <li v-for="dog in dogs">
        <router-link :to="'/dogs/' + dog.id">{{ dog.name }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "DogsList",
  props: {},
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
