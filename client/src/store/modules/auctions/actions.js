import { Auctions } from "@/api";

export default {
  fetchAll(context) {
    return Auctions.getAll().then(auctions => {
      for (let auction of auctions) {
        context.commit("updateOrCreate", auction);
      }
    });
  },

  fetch(context, id) {
    return Auctions.get(id).then(auction => {
      context.commit("updateOrCreate", auction);
    });
  }
};
