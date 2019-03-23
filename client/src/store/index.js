import Vue from "vue";
import Vuex from "vuex";

import dogs from "./modules/dogs";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userID: null,
    username: null
  },
  mutations: {
    setUserID(state, userID) {
      state.userID = userID;
    },
    setUsername(state, username) {
      state.username = username;
    }
  },
  actions: {
    signin(context, username) {
      context.commit("setUserID", 1);
      context.commit("setUsername", username);
    },
    signout(context) {
      context.commit("setUserID", null);
      context.commit("setUsername", null);
    }
  },
  modules: {
    dogs
  }
});
