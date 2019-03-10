import Vue from "vue";

import map from "lodash/map";

// https://forum.vuejs.org/t/updating-array-property-within-vuex/11411

function keyForDog({ id }) {
  return `dog_${id}`;
}

const state = {
  dogs: {},
  dogsList: []
};

const getters = {
  dogs(state) {
    return map(state.dogsList, id => state.dogs[keyForDog({ id })]);
  }
};

const actions = {};

const mutations = {
  add(state, dog) {
    const key = keyForDog(dog);
    Vue.set(state.dogs, key, dog);
    state.dogsList.push(dog.id);
  },
  set(state, dog) {
    const key = keyForDog(dog);
    Vue.set(state.dogs, key, dog);
  }
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
};
