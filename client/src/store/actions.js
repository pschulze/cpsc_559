export default {
  signin(context, username) {
    context.commit("setUserId", 1);
    context.commit("setUsername", username);
  },
  signout(context) {
    context.commit("setUserId", null);
    context.commit("setUsername", null);
  }
};
