import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios";

Vue.prototype.$api = axios.create({ baseURL: "http://ec2-3-95-28-219.compute-1.amazonaws.com:7000/" });

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
