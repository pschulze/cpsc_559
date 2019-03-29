import BaseRoute from "./baseroute";

export class DogsRoute extends BaseRoute {
  constructor() {
    super("dogs/");
  }
}

export default new DogsRoute();
