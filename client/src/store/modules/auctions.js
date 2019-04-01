import Vue from "vue";

import { List, keyForItem } from "./reusable";

import { Auctions } from "@/api";

import map from "lodash/map";
import filter from "lodash/filter";
import differenceWith from "lodash/differenceWith";
import union from "lodash/union";

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
  byDog: (state, getters) => dogId => {
    return filter(getters.all, { dogId });
  }
};

const mutations = {
  ...List.mutations,

  deleteRealtimeData(state) {
    for (let i = 0; i < state.ids.length; i++) {
      const id = state.ids[i];
      const key = keyForItem({ id });
      const item = state.items[key];
      console.log(id, item);
      Vue.set(item, "startPrice", null);
      Vue.set(item, "expirationTime", null);
    }
  }
};

const actions = {
  fetchAll(context) {
    return Auctions.getAll().then(auctions => {
      context.commit("batchUpdateOrCreate", auctions);
      return context.dispatch("active/synchronize", auctions);
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

  update(context, { id, ...values }) {
    return Auctions.update(id, values).then(auction => {
      context.commit("updateOrCreate", auction);
    });
  },

  checkRemoval(context) {
    const toKeep = union(context.state.active.ids, context.state.archive.ids);
    const toRemove = differenceWith(context.state.ids, toKeep);
    if (toRemove.length) context.commit("batchRemove", toRemove);
  },

  placeBid(context, { auctionId, ...values }) {
    return Auctions.bid(auctionId, values);
  }
};

/* - - - Auctions Submodule Template - - - */

const submodule = {
  state() {
    return {
      ids: [
        /* ...ids */
      ]
    };
  },

  mutations: {
    add(state, id) {
      if (state.ids.indexOf(id) === -1) state.ids.push(id);
    },
    batchAdd(state, ids) {
      for (let i = 0; i < ids.length; i++)
        submodule.mutations.add(state, ids[i]);
    },
    remove(state, id) {
      if (state.ids.indexOf(id) !== -1)
        Vue.delete(state.ids, state.ids.indexOf(id));
    },
    batchRemove(state, ids) {
      for (let i = 0; i < ids.length; i++)
        submodule.mutations.remove(state, ids[i]);
    },
    synchronize(state, ids) {
      const toRemove = differenceWith(state.ids, ids);
      submodule.mutations.batchRemove(state, toRemove);
      submodule.mutations.batchAdd(state, ids);
    }
  },

  getters: {
    all(state, getters, rootState) {
      return map(state.ids, id => rootState.auctions.items[keyForItem({ id })]);
    },
    byId: (state, getters, rootState, rootGetters) => id => {
      if (state.ids.indexOf(id) === -1) return null;
      return rootGetters["auctions/byId"](id);
    },
    byDog: (state, getters) => dogId => {
      return filter(getters.all, { dogId });
    }
  }
};

/* - - - Active Auctions Submodule - - - */

const active = {
  namespaced: true,
  ...submodule,
  actions: {
    synchronize(context, auctions) {
      context.commit("synchronize", map(auctions, auction => auction.id));
      return context.dispatch("auctions/checkRemoval", null, { root: true });
    }
  }
};

/* - - - Archived Auctions Submodule - - - */

const archive = {
  namespaced: true,
  ...submodule,
  actions: {
    require(context, id) {
      if (context.state.ids.indexOf(id) !== -1) return Promise.resolve();
      context.commit("add", id);
      return Auctions.get(id)
        .then(auction => {
          context.commit("auctions/updateOrCreate", auction, {
            root: true
          });
        })
        .catch(() => {});
    },

    release(context, id) {
      if (context.state.ids.indexOf(id) !== -1) context.commit("remove", id);
      return context.dispatch("auctions/checkRemoval", null, { root: true });
    }
  }
};

/* - - - Export Auction & Submodules - - - */

export default {
  namespaced: true,
  state: List.state,
  mutations,
  getters,
  actions,
  modules: {
    active,
    archive
  }
};
