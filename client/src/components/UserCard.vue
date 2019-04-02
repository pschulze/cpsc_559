<template>
  <div class="card userCard">
    <template v-if="sm">
      <div v-if="outlineonly" class="card-body">
        <vue-content-loading :width="124" :height="60" class="userCardBody">
          <rect x="0" y="0" rx="4" ry="4" width="80" height="24" />
          <rect x="0" y="36" rx="4" ry="4" width="124" height="21" />
        </vue-content-loading>
      </div>
      <div v-else class="card-body">
        <router-link :to="{ name: 'user', params: { id: user.id } }">
          <h5 class="card-title">{{ user.username }}</h5>
        </router-link>
      </div>
    </template>
    <template v-else>
      <template v-if="outlineonly">
        <div class="card-body">
          <vue-content-loading :width="124" :height="81" class="userCardBody">
            <rect x="0" y="0" rx="4" ry="4" width="80" height="24" />
            <rect x="0" y="32" rx="4" ry="4" width="70" height="19" />
            <rect x="0" y="63" rx="4" ry="4" width="124" height="21" />
          </vue-content-loading>
        </div>
      </template>
      <template v-else>
        <div class="card-body">
          <router-link :to="{ name: 'user', params: { id: user.id } }">
            <h5 class="card-title">{{ user.username }}</h5>
          </router-link>
        </div>
      </template>
    </template>
  </div>
</template>

<script>
import VueContentLoading from "vue-content-loading";

export default {
  name: "UserCard",
  components: {
    VueContentLoading
  },
  props: {
    user: Object,
    outlineonly: Boolean,
    sm: Boolean
  }
};
</script>

<style>
.userCard {
  width: 265px;
}
.userCard a {
  color: inherit;
}
svg.userCardBody {
  width: 124px;
  vertical-align: top;
}
</style>
