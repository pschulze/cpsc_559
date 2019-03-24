import { Dogs } from "@/api";

export default {
  fetchAll(context) {
    return Dogs.getAll().then(dogs => {
      for (let dog of dogs) {
        context.commit("updateOrCreate", dog);
      }
    });
  },

  fetch(context, id) {
    return Dogs.get(id).then(dog => {
      context.commit("updateOrCreate", dog);
    });
  }
};
