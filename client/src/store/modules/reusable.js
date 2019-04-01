import Vue from "vue";

import map from "lodash/map";
import differenceWith from "lodash/differenceWith";

// https://forum.vuejs.org/t/updating-array-property-within-vuex/11411

export function keyForItem({ id }) {
  return `item_${id}`;
}

export const List = {
  state() {
    return {
      items: {
        /*
        item_id: {
          id,
          etc
        }
        */
      },
      ids: [
        /* ...ids */
      ]
    };
  },

  getters: {
    items(state) {
      return map(state.ids, id => state.items[keyForItem({ id })]);
    },
    itemById: state => id => {
      return state.items[keyForItem({ id })];
    }
  },

  mutations: {
    updateOrCreate(state, item) {
      const key = keyForItem(item);
      Vue.set(state.items, key, item);
      if (state.ids.indexOf(item.id) === -1) state.ids.push(item.id);
    },
    batchUpdateOrCreate(state, items) {
      for (let i = 0; i < items.length; i++)
        List.mutations.updateOrCreate(state, items[i]);
    },
    remove(state, id) {
      if (state.ids.indexOf(id) === -1) return;
      const index = state.ids.indexOf(id);
      Vue.delete(state.ids, index);
      const key = keyForItem({ id });
      Vue.delete(state.items, key);
    },
    batchRemove(state, ids) {
      for (let i = 0; i < ids.length; i++) List.mutations.remove(state, ids[i]);
    },
    synchronize(state, items) {
      const toRemove = differenceWith(state.ids, items, (id, item) => {
        return id === item.id;
      });
      List.mutations.batchRemove(state, toRemove);
      List.mutations.batchUpdateOrCreate(state, items);
    }
  }
};
