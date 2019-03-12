<template>
  <div class="container">
    <Dog :dog="dog" />
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
  data() {
    return {
      loading: false
    };
  },
  computed: {
    ...mapGetters({
      dogById: "dogs/dogById"
    }),
    dog() {
      return this.dogById(this.$route.params.id);
    }
  },
  mounted() {
    this.loading = true;
    this.$store.dispatch("dogs/fetch", this.$route.params.id).finally(() => {
      this.loading = false;
    });
  }
};
</script>
