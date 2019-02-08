<template>
  <div class="dog">
    <h1>{{ dog.name }}</h1>
    <h3>{{ dog.id }}</h3>
  </div>
</template>

<script>
export default {
  name: "Dog",
  props: {},
  data() {
    return {
      dog: {}
    };
  },
  mounted() {
    this.$api.get("/dogs/" + this.$route.params.id).then(res => (this.dog = res.data));
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
