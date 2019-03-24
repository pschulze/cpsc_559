import { List } from "../reusable";
import actions from "./actions";

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

export default {
  namespaced: true,
  state: List.state,
  getters,
  actions,
  mutations: List.mutations
};
