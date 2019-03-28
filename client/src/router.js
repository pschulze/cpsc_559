import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";

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
      component: () =>
        import(/* webpackChunkName: "auctionlist" */ "./views/AuctionList.vue")
    },
    {
      path: "/auctions/:id",
      name: "auction",
      component: () =>
        import(/* webpackChunkName: "auction" */ "./views/Auction.vue")
    },
    {
      path: "/dogs",
      name: "doglist",
      component: () =>
        import(/* webpackChunkName: "doglist" */ "./views/DogList.vue")
    },
    {
      path: "/dogs/:id",
      name: "dog",
      component: () => import(/* webpackChunkName: "dog" */ "./views/Dog.vue")
    },
    {
      path: "/users",
      name: "userlist",
      component: () =>
        import(/* webpackChunkName: "userlist" */ "./views/UserList.vue")
    },
    {
      path: "/users/:id",
      name: "user",
      component: () => import(/* webpackChunkName: "user" */ "./views/User.vue")
    },
    {
      path: "/account",
      name: "account",
      component: () =>
        import(/* webpackChunkName: "account" */ "./views/Account.vue")
    }
  ]
});
