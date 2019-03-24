import axios from "./axios";

import BaseRoute from "./baseroute";

export class AuctionsRoute extends BaseRoute {
  constructor() {
    super("auctions/");
  }
}

export default new AuctionsRoute();
