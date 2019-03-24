import Vue from "vue";

import actions from "./actions";

import map from "lodash/map";

// https://forum.vuejs.org/t/updating-array-property-within-vuex/11411

function keyForUser({ id }) {
  return `user_${id}`;
}

const state = {
  users: {
    /*
    user_id: {
      id,
      name,
      breed,
      age
    }
    */
  },
  usersList: [
    /* ...ids */
  ]
};

const getters = {
  users(state) {
    return map(state.usersList, id => state.users[keyForUser({ id })]);
  },
  userById: state => id => {
    return state.users[keyForUser({ id })];
  }
};

const mutations = {
  add(state, user) {
    const key = keyForUser(user);
    Vue.set(state.users, key, user);
    state.usersList.push(user.id);
  },
  set(state, user) {
    const key = keyForUser(user);
    Vue.set(state.users, key, user);
  },
  updateOrCreate(state, user) {
    const key = keyForUser(user);
    Vue.set(state.users, key, user);
    if (state.usersList.indexOf(user.id) === -1) state.usersList.push(user.id);
  }
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
};
