import Vue from "vue";

import actions from "./actions";

import map from "lodash/map";

// https://forum.vuejs.org/t/updating-array-property-within-vuex/11411

function keyForAuction({ id }) {
  return `auction_${id}`;
}

const state = {
  auctions: {
    /*
    auction_id: {
      id,
      dogId,
      expirationTime,
      startPrice,
      name,
      completed
    }
    */
  },
  auctionsList: [
    /* ...ids */
  ]
};

const getters = {
  auctions(state) {
    return map(state.auctionsList, id => state.auctions[keyForAuction({ id })]);
  },
  auctionById: state => id => {
    return state.auctions[keyForAuction({ id })];
  }
};

const mutations = {
  add(state, auction) {
    const key = keyForAuction(auction);
    Vue.set(state.auctions, key, auction);
    state.auctionsList.push(auction.id);
  },
  set(state, auction) {
    const key = keyForAuction(auction);
    Vue.set(state.auctions, key, auction);
  },
  updateOrCreate(state, auction) {
    const key = keyForAuction(auction);
    Vue.set(state.auctions, key, auction);
    if (state.auctionsList.indexOf(auction.id) === -1)
      state.auctionsList.push(auction.id);
  }
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
};
