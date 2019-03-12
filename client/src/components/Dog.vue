<template>
  <div class="dog">
    <h1>{{ dog.name }}</h1>
    <h3>{{ dog.id }}</h3>
  </div>
</template>

<script>
export default {
  name: "DogCard",
  props: {
    dog: Object
  }
};
</script>

<style scoped></style>
