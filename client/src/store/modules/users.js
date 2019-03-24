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
  users: List.getters.items,
  userById: List.getters.itemById
};

const actions = {
  fetchAll(context) {
    return Users.getAll().then(users => {
      for (let user of users) {
        context.commit("updateOrCreate", user);
      }
    });
  },

  fetch(context, id) {
    return Users.get(id).then(user => {
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
