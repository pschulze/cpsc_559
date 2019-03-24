export default {
  signin(context, username) {
    context.commit("setUserID", 1);
    context.commit("setUsername", username);
  },
  signout(context) {
    context.commit("setUserID", null);
    context.commit("setUsername", null);
  }
};
