<template>
  <div class="container">
    <Dog :dog="dog" />
  </div>
</template>

<script>
import Dog from "@/components/Dog.vue";

export default {
  name: "dog",
  components: {
    Dog
  },
  data() {
    return {
      dog: {}
    };
  },
  mounted() {
    this.$api
      .get("/dogs/" + this.$route.params.id)
      .then(res => (this.dog = res.data));
  }
};
</script>
