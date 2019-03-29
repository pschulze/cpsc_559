import axios from "axios";
import { responseError } from "./errorHandler";

const instance = axios.create({
  baseURL: "http://ec2-3-88-0-236.compute-1.amazonaws.com:975/"
});

// Transform responses before sending onto rest of app
instance.interceptors.response.use(
  function(response) {
    return response.data;
  },
  function(error) {
    return responseError(error);
  }
);

export default instance;
