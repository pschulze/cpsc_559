import axios from "./axios";

export default {
  getAll() {
    return axios.get("/dogs");
  },
  get(id) {
    return axios.get("/dogs/" + id);
  },
  create(values) {
    return axios.post("/dogs", values);
  },
  update(id, values) {
    return axios.patch("/dogs/" + id, values);
  }
};
