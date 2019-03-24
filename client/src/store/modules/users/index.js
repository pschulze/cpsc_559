import { List } from "../reusable";
import actions from "./actions";

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

export default {
  namespaced: true,
  state: List.state,
  getters,
  actions,
  mutations: List.mutations
};
