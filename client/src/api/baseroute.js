import axios from "./axiosconfig";

export default class BaseRoute {
  constructor(basepath) {
    this.basepath = basepath.replace(/\/$/, "");
  }

  getAll() {
    return axios.get(this.basepath);
  }

  get(id) {
    return axios.get(this.basepath + "/" + id);
  }

  create(values) {
    return axios.post(this.basepath, values);
  }

  update(id, values) {
    return axios.patch(this.basepath + "/" + id, values);
  }
}
