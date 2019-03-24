import axios from "./axios";

export default {
  getAll() {
    return axios.get("/auctions");
  },
  get(id) {
    return axios.get("/auctions/" + id);
  },
  create(values) {
    return axios.post("/auctions", values);
  },
  update(id, values) {
    return axios.patch("/auctions/" + id, values);
  }
};
