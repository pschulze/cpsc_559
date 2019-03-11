<template>
  <div class="container">
    <Dog :dog="dogById(this.$route.params.id)" />
  </div>
</template>

<script>
import { mapGetters } from "vuex";

import Dog from "@/components/Dog.vue";

export default {
  name: "dog",
  components: {
    Dog
  },
  computed: {
    ...mapGetters({
      dogById: "dogs/dogById"
    })
  },
  mounted() {
    this.$api
      .get("/dogs/" + this.$route.params.id)
      .then(res => this.$store.commit("dogs/updateOrCreate", res.data));
  }
};
</script>
