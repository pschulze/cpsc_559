const response = {
  // The request was made and the server responded
  recieved(error) {
    console.log("API ERROR (Response):", error.response);
    return Promise.reject({
      msg: null,
      data: error.response.data,
      status: error.response.status,
      error
    });
  },

  // The request was made but no response was received
  notRecieved(error) {
    console.log(
      "API ERROR (No Response):",
      error.config.method,
      error.config.url
    );
    return Promise.reject({
      msg: "Could not connect to the API server. Please try again later",
      status: 521,
      error
    });
  },

  // Something happened in setting up the request that triggered an Error
  otherError(error) {
    console.log(
      "API ERROR: Something happened in setting up the request that triggered an Error",
      error
    );
    return Promise.reject({
      msg: "Something went wrong, please try again later.",
      status: 418,
      error
    });
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
