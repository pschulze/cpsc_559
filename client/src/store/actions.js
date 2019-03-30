import { Account } from "@/api";

export default {
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
