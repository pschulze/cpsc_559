import axios from "./axiosconfig";

export class AccountRoute {
  login(username) {
    return axios.get("account/login/user?username=" + username);
  }
}

export default new AccountRoute();
