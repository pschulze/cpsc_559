import { List } from "../reusable";
import actions from "./actions";

/*
state = {
  items: {
    item_id: {
      id,
      name,
      breed,
      age,
      ownerId
    }
  },
  ids: [
    ...ids
  ]
}
*/

const getters = {
  dogs: List.getters.items,
  dogById: List.getters.itemById
};

export default {
  namespaced: true,
  state: List.state,
  getters,
  actions,
  mutations: List.mutations
};
