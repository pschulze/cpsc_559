import { List } from "../reusable";

import { Auctions } from "@/api";

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
  auctions: List.getters.items,
  auctionById: List.getters.itemById
};

const actions = {
  fetchAll(context) {
    return Auctions.getAll().then(auctions => {
      for (let auction of auctions) {
        context.commit("updateOrCreate", auction);
      }
    });
  },

  fetch(context, id) {
    return Auctions.get(id).then(auction => {
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
