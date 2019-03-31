import Vue from "vue";
import Router from "vue-router";

import Home from "./views/Home.vue";
import AuctionList from "./views/AuctionList.vue";
import Auction from "./views/Auction.vue";
import DogList from "./views/DogList.vue";
import Dog from "./views/Dog.vue";
import UserList from "./views/UserList.vue";
import User from "./views/User.vue";
import Account from "./views/Account.vue";

Vue.use(Router);

export default new Router({
  /* mode: "history", */ // Dissabled to allow file-system browsing of the app
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/auctions",
      name: "auctionlist",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      //component: () =>
      //  import(/* webpackChunkName: "auctionlist" */ "./views/AuctionList.vue")
      component: AuctionList
    },
    {
      path: "/auctions/:id",
      name: "auction",
      component: Auction
    },
    {
      path: "/dogs",
      name: "doglist",
      component: DogList
    },
    {
      path: "/dogs/:id",
      name: "dog",
      component: Dog
    },
    {
      path: "/users",
      name: "userlist",
      component: UserList
    },
    {
      path: "/users/:id",
      name: "user",
      component: User
    },
    {
      path: "/account",
      name: "account",
      component: Account
    }
  ]
});
