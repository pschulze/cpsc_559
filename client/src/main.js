import Vue from "vue";
import PortalVue from "portal-vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import * as api from "./api";

Vue.use(PortalVue);

Vue.prototype.$api = api;

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
