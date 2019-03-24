import { List } from "./reusable";

import { Dogs } from "@/api";

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
  byId: List.getters.itemById
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

export default {
  namespaced: true,
  state: List.state,
  mutations: List.mutations,
  getters,
  actions
};
