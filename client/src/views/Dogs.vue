<template>
  <div class="dogs">
    <DogsList />
  </div>
</template>

<script>
// @ is an alias to /src
import DogsList from "@/components/DogsList.vue";

export default {
  name: "dogs",
  components: {
    DogsList
  }
};
</script>
