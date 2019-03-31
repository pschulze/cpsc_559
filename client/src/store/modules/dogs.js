import { List } from "./reusable";

import { Dogs } from "@/api";

import filter from "lodash/filter";

/*
state = {
  items: {
    item_id: {
      id,
      name,
      breed,
      age,
      ownerId
    }
  },
  ids: [
    ...ids
  ]
}
*/

const getters = {
  all: List.getters.items,
  byId: List.getters.itemById,
  byOwner: state => ownerId => {
    return filter(List.getters.items(state), { ownerId });
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
  },

    fetchName(context, name) {
      return Dogs.get(name).then(dog => {
        context.commit("updateOrCreate", dog);
      });
    },

    fetchBreed(context, breed) {
      return Dogs.get(breed).then(dog => {
        context.commit("updateOrCreate", dog);
      });
    },


  create(context, values) {
    return Dogs.create(values).then(dog => {
      context.commit("updateOrCreate", dog);
    });
  },

  update(context, id, values) {
    return Dogs.update(id, values).then(dog => {
      context.commit("updateOrCreate", dog);
    });
  }
};

export default {
  namespaced: true,
  state: List.state,
  mutations: List.mutations,
  getters,
  actions
};
