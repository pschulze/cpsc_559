import { List } from "./reusable";

import { Users } from "@/api";

/*
state = {
  items: {
    item_id: {
      id,
      username
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
    return Users.getAll().then(users => {
      context.commit("synchronize", users);
    });
  },

  fetch(context, id) {
    return Users.get(id).then(user => {
      context.commit("updateOrCreate", user);
    });
  },

    create(context, values) {
      return Users.create(values).then(user => {
        context.commit("updateOrCreate", user);
      });
    },

    update(context, id, values) {
      return Users.update(id, values).then(user => {
        context.commit("updateOrCreate", user);
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
