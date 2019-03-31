import { Account, Ping } from "@/api";

const removeRealtimeData = function() {
  // TODO remove time-sensitive auction info from store
  console.log("TODO: remove realtime data from store");
};

export default {
  apiUnavailable(context) {
    context.commit("apiUnavailable");
    let polling;
    polling = setInterval(
      function() {
        Ping()
          .then(() => {
            clearInterval(polling);
            context.commit("apiAvailable");
          })
          .catch(() => {});
      }.bind(polling),
      5000
    );
    removeRealtimeData();
  },
  signin(context, username) {
    return Account.login(username).then(user => {
      context.commit("setUser", user);
    });
  },
  signout(context) {
    context.commit("setUser", null);
  }
};
