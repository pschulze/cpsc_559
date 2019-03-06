<template>
  <div class="card">
    <div v-if="outlineonly" class="card-body">
      <vue-content-loading :width="124" :height="60" class="dogCardLoading">
        <rect x="0" y="0" rx="4" ry="4" width="80" height="24" />
        <rect x="0" y="36" rx="4" ry="4" width="124" height="21" />
      </vue-content-loading>
    </div>
    <div v-else class="card-body">
      <h5 class="card-title">{{ dog.name }}</h5>
      <router-link :to="{ name: 'dog', params: { id: dog.id } }"
        >More Information</router-link
      >
    </div>
  </div>
</template>

<script>
import VueContentLoading from 'vue-content-loading';

export default {
  name: "DogCard",
  components: {
    VueContentLoading,
  },
  props: {
    dog: Object,
    outlineonly: Boolean,
  }
};
</script>

<style>
svg.dogCardLoading {
  width: 124px;
  vertical-align: top;
}
</style>
