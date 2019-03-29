import { List } from "./reusable";

import { Auctions } from "@/api";

import filter from "lodash/filter";

/*
state = {
  items: {
    item_id: {
      id,
      dogId,
      expirationTime,
      startPrice,
      name,
      completed
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
  byDog: state => dogId => {
    return filter(List.getters.items(state), { dogId });
  }
};

const actions = {
  fetchAll(context) {
    return Auctions.getAll().then(auctions => {
      context.commit("synchronize", auctions);
    });
  },

  fetch(context, id) {
    return Auctions.get(id).then(auction => {
      context.commit("updateOrCreate", auction);
    });
  },

  create(context, values) {
    return Auctions.create(values).then(auction => {
      context.commit("updateOrCreate", auction);
    });
  },

  update(context, id, values) {
    return Auctions.update(id, values).then(auction => {
      context.commit("updateOrCreate", auction);
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
