<template>
  <div class="card" style="width: 18rem;">
    <template v-if="sm">
      <div v-if="outlineonly" class="card-body">
        <vue-content-loading :width="124" :height="60" class="bidCardBody">
          <rect x="0" y="0" rx="4" ry="4" width="80" height="24" />
          <rect x="0" y="36" rx="4" ry="4" width="124" height="21" />
        </vue-content-loading>
      </div>
      <div v-else class="card-body">
        <h5 class="card-title">{{ bid.id }}</h5>
        <router-link :to="{ name: 'bid', params: { id: bid.id } }"
          >More Information</router-link
        >
      </div>
    </template>
    <template v-else>
      <template v-if="outlineonly">
        <div class="card-body">
          <vue-content-loading :width="124" :height="81" class="bidCardBody">
            <rect x="0" y="0" rx="4" ry="4" width="80" height="24" />
            <rect x="0" y="32" rx="4" ry="4" width="70" height="19" />
            <rect x="0" y="63" rx="4" ry="4" width="124" height="21" />
          </vue-content-loading>
        </div>
      </template>
      <template v-else>
        <div class="card-body">
          <h5 class="card-title">{{ bid.id }}</h5>
          <h6 class="card-subtitle mb-2 text-muted">{{ bid.amount }}</h6>
          <router-link :to="{ name: 'bid', params: { id: bid.id } }"
            >More Information</router-link
          >
        </div>
        <div class="card-footer text-muted" v-if="auction">
          <router-link :to="{ name: 'auction', params: { id: auction.id } }">
            {{ auction.name }}
          </router-link>
        </div>
      </template>
    </template>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import head from "lodash/head";
import VueContentLoading from "vue-content-loading";

export default {
  name: "BidCard",
  components: {
    VueContentLoading
  },
  props: {
    bid: Object,
    outlineonly: Boolean,
    sm: Boolean
  },
  computed: {
    ...mapGetters({

    }),

  }
};
</script>

<style>
svg.bidCardBody {
  width: 124px;
  vertical-align: top;
}
</style>
