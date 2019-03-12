import Vue from "vue";

import { Dogs } from "@/api";

import find from "lodash/find";
import map from "lodash/map";

// https://forum.vuejs.org/t/updating-array-property-within-vuex/11411

function keyForDog({ id }) {
  return `dog_${id}`;
}

const state = {
  dogs: {
    /*
    dog_id: {
      id,
      name,
      breed,
      age
    }
    */
  },
  dogsList: [
    /* ...ids */
  ]
};

const getters = {
  dogs(state) {
    return map(state.dogsList, id => state.dogs[keyForDog({ id })]);
  },
  dogById: state => id => {
    return find(state.dogs, { id });
  }
};

const actions = {
  fetchAll(context) {
    return Dogs.getAll().then(dogs => {
      for (let dog of dogs) {
        context.commit("updateOrCreate", dog);
      }
    });
  },

  fetch(context, id) {
    return Dogs.get(id).then(dog => {
      context.commit("updateOrCreate", dog);
    });
  }
};

const mutations = {
  add(state, dog) {
    const key = keyForDog(dog);
    Vue.set(state.dogs, key, dog);
    state.dogsList.push(dog.id);
  },
  set(state, dog) {
    const key = keyForDog(dog);
    Vue.set(state.dogs, key, dog);
  },
  updateOrCreate(state, dog) {
    const key = keyForDog(dog);
    Vue.set(state.dogs, key, dog);
    if (state.dogsList.indexOf(dog.id) === -1) state.dogsList.push(dog.id);
  }
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
};
