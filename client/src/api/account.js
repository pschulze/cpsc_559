import axios from "./axiosconfig";

export class AccountRoute {
  constructor() {
    this.basepath = "account";
  }

  login(username) {
    return axios.get(this.basepath + "/login/user?username=" + username);
  }

  create(username) {
    return axios.post(this.basepath, { username });
  }

  getBids(id) {
    return axios.get(this.basepath + "/" + id + "/bids");
  }
}

export default new AccountRoute();
