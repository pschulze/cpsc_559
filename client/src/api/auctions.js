import axios from "./axiosconfig";

import BaseRoute from "./baseroute";

export class AuctionsRoute extends BaseRoute {
  constructor() {
    super("auctions/");
  }

  bid(auctionId, values) {
    return axios.post(this.basepath + "/" + auctionId, values);
  }
}

export default new AuctionsRoute();
