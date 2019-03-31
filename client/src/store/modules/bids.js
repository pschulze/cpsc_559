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
  byId: List.getters.itemById,
  byBidder: state => bidderId => {
      return filter(List.getters.items(state), { bidderId });
    }
};

const actions = {
  fetchAll(context) {
    return Account.getBids(context.rootState.userId).then(bids => {
      const toAdd = differenceWith(bids, context.state.ids, (item, id) => {
        return id === item.id;
      });
      context.commit("synchronize", bids);
      for (let i = 0; i < toAdd.length; i++)
        context.dispatch("auctions/requireCached", toAdd[i].auctionId, {
          root: true
        });
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
