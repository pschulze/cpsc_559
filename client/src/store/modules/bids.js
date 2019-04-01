import { List } from "./reusable";

import { Account } from "@/api";

import differenceWith from "lodash/differenceWith";

/*
state = {
  items: {
    item_id: {
      id,
      auctionId,
      bidderId,
      amount
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
    return Account.getBids(context.rootState.userId).then(bids => {
      const toAdd = differenceWith(bids, context.state.ids, (item, id) => {
        return id === item.id;
      });
      for (let i = 0; i < toAdd.length; i++)
        context.dispatch("auctions/archive/require", toAdd[i].auctionId, {
          root: true
        });
      const toRemove = differenceWith(context.state.ids, bids, (id, item) => {
        return id === item.id;
      });
      for (let i = 0; i < toRemove.length; i++) {
        const bid = context.getters.byId(toRemove[i]);
        context.dispatch("auctions/archive/release", bid.auctionId, {
          root: true
        });
      }
      context.commit("synchronize", bids);
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
