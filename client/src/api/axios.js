import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:7000/"
  //baseURL: "http://ec2-3-95-28-219.compute-1.amazonaws.com:7000/"
});
