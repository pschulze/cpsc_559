import axios from "./axios";

export class AccountRoute {
  login(username) {
    return axios.get("account/login/user?username=" + username);
  }
}

export default new AccountRoute();
