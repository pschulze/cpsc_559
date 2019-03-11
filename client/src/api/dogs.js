import axios from "./axios";

export default {
  getAll() {
    return axios.get("/dogs").then(res => res.data);
  },
  get(id) {
    return axios.get("/dogs/" + id).then(res => res.data);
  }
};
