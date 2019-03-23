import axios from "axios";

const instance = axios.create({
  baseURL: "http://ec2-3-88-0-236.compute-1.amazonaws.com:975/"
});

// Transform responses before sending onto rest of app
instance.interceptors.response.use(
  function(response) {
    return response.data;
  },
  function(error) {
    if (error.response) {
      // The request was made and the server responded
      return Promise.reject({
        status: error.response.status,
        data: error.response.data
      });
    } else if (error.request) {
      // The request was made but no response was received
      return Promise.reject({ request: error.request });
    } else {
      // Something happened in setting up the request that triggered an Error
      return Promise.reject({ error });
    }
  }
);

export default instance;
