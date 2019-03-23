import store from "@/store";

const response = {
  // The request was made and the server responded
  recieved(error) {
    console.log(
      "API ERROR: The request was made and the server responded",
      error.response
    );
    store.commit("apiUnavailable");
    store.commit("addAPIError", {
      msg: error.response.status,
      req: {
        method: error.config.method,
        url: error.config.url
      }
    });
    return Promise.reject();
  },

  // The request was made but no response was received
  notRecieved(error) {
    console.log(
      "API ERROR: The request was made but no response was received",
      error.config.method,
      error.config.url
    );
    store.commit("apiUnavailable");
    store.commit("addAPIError", {
      msg: "API did not respond",
      req: {
        method: error.config.method,
        url: error.config.url
      }
    });
    return Promise.reject();
  },

  // Something happened in setting up the request that triggered an Error
  otherError(error) {
    console.log(
      "API ERROR: Something happened in setting up the request that triggered an Error",
      error
    );
    store.commit("apiUnavailable");
    return Promise.reject();
  }
};

export function responseError(error) {
  if (error.response) {
    return response.recieved(error);
  } else if (error.request) {
    return response.notRecieved(error);
  } else {
    return response.otherError(error);
  }
}
