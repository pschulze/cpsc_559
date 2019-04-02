import { Account, Ping } from "@/api";

export default {
  apiUnavailable(context) {
    if (!context.state.apiAvailable) return;
    context.commit("apiUnavailable");
    context.commit("auctions/deleteRealtimeData");
  },
  pingAPI(context) {
    return Ping().then(() => {
      context.commit("apiAvailable");
    });
  },
  signin(context, username) {
    return Account.login(username).then(user => {
      context.commit("setUser", user);
    });
  },
  signout(context) {
    context.commit("setUser", null);
  },
  createAccount(context, username) {
    return Account.create(username).then(user => {
      context.commit("setUser", user);
    });
  }
};
