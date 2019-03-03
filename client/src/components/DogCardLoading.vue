<template>
  <div class="card">
    <div class="card-body">
      <vue-content-loading :width="124" :height="60">
        <rect x="0" y="0" rx="4" ry="4" width="80" height="24" />
        <rect x="0" y="36" rx="4" ry="4" width="124" height="21" />
      </vue-content-loading>
    </div>
  </div>
</template>

<script>
import VueContentLoading from 'vue-content-loading';

export default {
  name: "DogCardLoading",
  components: {
    VueContentLoading,
  },
};
</script>

<style scoped>
svg {
  width: 124px;
  vertical-align: top;
}
</style>
