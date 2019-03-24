import { Users } from "@/api";

export default {
  fetchAll(context) {
    return Users.getAll().then(users => {
      for (let user of users) {
        context.commit("updateOrCreate", user);
      }
    });
  },

  fetch(context, id) {
    return Users.get(id).then(user => {
      context.commit("updateOrCreate", user);
    });
  }
};
