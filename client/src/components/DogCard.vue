<template>
  <div class="card">
    <div class="card-body">
      <h5 class="card-title">{{ dog.name }}</h5>
      <router-link :to="{ name: 'dog', params: { id: dog.id } }"
        >More Information</router-link
      >
    </div>
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
