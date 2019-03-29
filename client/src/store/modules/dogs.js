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
      context.commit("synchronize", dogs);
    });
  },

  fetch(context, id) {
    return Dogs.get(id).then(dog => {
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
