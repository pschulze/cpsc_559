<template>
  <div class="card">
    <template v-if="sm">
      <div v-if="outlineonly" class="card-body">
        <vue-content-loading :width="124" :height="60" class="dogCardBody">
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
    </template>
    <template v-else>
      <template v-if="outlineonly">
        <vue-content-loading :width="200" :height="200" class="card-img-top dogCardImg">
          <rect x="0" y="0" rx="4" ry="4" width="200" height="200" />
        </vue-content-loading>
        <div class="card-body">
          <vue-content-loading :width="124" :height="81" class="dogCardBody">
            <rect x="0" y="0" rx="4" ry="4" width="80" height="24" />
            <rect x="0" y="32" rx="4" ry="4" width="70" height="19" />
            <rect x="0" y="63" rx="4" ry="4" width="124" height="21" />
          </vue-content-loading>
        </div>
      </template>
      <template v-else>
        <img src="https://place-hold.it/200x200" class="card-img-top">
        <div class="card-body">
          <h5 class="card-title">{{ dog.name }}</h5>
          <h6 class="card-subtitle mb-2 text-muted">{{ dog.breed }}</h6>
          <router-link :to="{ name: 'dog', params: { id: dog.id } }"
            >More Information</router-link
          >
        </div>
      </template>
    </template>
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
    sm: Boolean,
  }
};
</script>

<style>
svg.dogCardImg {
  width: 200px;
  vertical-align: top;
}

svg.dogCardBody {
  width: 124px;
  vertical-align: top;
}
</style>
