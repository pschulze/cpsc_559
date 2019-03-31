import axios from "./axios";

export default function ping() {
  return axios.get("dogs");
}
