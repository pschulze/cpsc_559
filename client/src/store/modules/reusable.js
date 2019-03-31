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
    add(state, item) {
      const key = keyForItem(item);
      Vue.set(state.items, key, item);
      state.ids.push(item.id);
    },
    set(state, item) {
      const key = keyForItem(item);
      Vue.set(state.items, key, item);
    },
    updateOrCreate(state, item) {
      const key = keyForItem(item);
      Vue.set(state.items, key, item);
      if (state.ids.indexOf(item.id) === -1) state.ids.push(item.id);
    },
    synchronize(state, items) {
      const toRemove = differenceWith(state.ids, items, (id, item) => {
        return id === item.id;
      });
      for (let i = 0; i < toRemove.length; i++) {
        const index = state.ids.indexOf(toRemove[i]);
        Vue.delete(state.ids, index);
        const key = keyForItem({ id: toRemove[i] });
        Vue.delete(state.items, key);
      }
      for (let i = 0; i < items.length; i++) {
        const item = items[i];
        const key = keyForItem(item);
        Vue.set(state.items, key, item);
        if (state.ids.indexOf(item.id) === -1) state.ids.push(item.id);
      }
    }
  }
};
