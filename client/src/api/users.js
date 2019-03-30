import BaseRoute from "./baseroute";

export class UsersRoute extends BaseRoute {
  constructor() {
    super("users/");
  }
}

export default new UsersRoute();
