import axios from "./axiosconfig";

export default function ping() {
  return axios.get("dogs");
}
