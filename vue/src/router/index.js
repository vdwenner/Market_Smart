import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import LandingPage from '../views/LandingPage'
import CreateGame from '../views/CreateGame'
import ViewGames from '../views/ViewGames'
import GameDetails from '../views/GameDetails'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/landing",
      name: "landing-page",
      component: LandingPage,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: "/createGame",
      name: "create-game",
      component: CreateGame,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/user/games",
      name: "view-games",
      component: ViewGames,
      meta: {
        requiresAuth: true
      }
    },
  //   {path: "/games/invite/request",
  //   name: "invite-to-game",
  //   component: GameInvite,
  //   meta:{
  //     requiresAuth: true
  //   }
  //  },
      {
        path: '/user/games/:id',
        name: 'game-details',
        component: GameDetails,
        meta: {
          requiresAuth: true
        }
      }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/landing");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
