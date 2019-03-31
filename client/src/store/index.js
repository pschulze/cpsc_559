import Vue from "vue";
import Vuex from "vuex";

import actions from "./actions";

import auctions from "./modules/auctions";
import dogs from "./modules/dogs";
import users from "./modules/users";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userId: null,
    username: null,
    apiAvailable: true,
    apiErrors: []
  },
  mutations: {
    setUser(state, user) {
      if (!user) {
        state.userId = null;
        state.username = null;
      } else {
        state.userId = user.id;
        state.username = user.username;
      }
    },
    apiAvailable(state) {
      state.apiAvailable = true;
    },
    apiUnavailable(state) {
      state.apiAvailable = false;
    },
    addAPIError(state, error) {
      state.apiErrors.push(error);
    },
    removeAPIError(state, error) {
      let index = state.apiErrors.indexOf(error);
      if (index >= 0) Vue.delete(state.apiErrors, index);
    },
    clearAPIErrors(state) {
      state.apiErrors = [];
    }
  },
  getters: {
    loggedin(state) {
      return state.userId !== null && state.userId !== undefined;
    }
  },
  actions,
  modules: {
    auctions,
    dogs,
    users
  }
});
