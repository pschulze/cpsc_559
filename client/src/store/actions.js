import { Account } from "@/api";

export default {
  signin(context, username) {
    return Account.login(username).then(user => {
      context.commit("setUser", user);
    });
  },
  signout(context) {
    context.commit("setUser", null);
  }
};
