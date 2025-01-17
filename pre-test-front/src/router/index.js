import UserAdd from "../views/UserAdd.vue";
import UserFetch from "../views/UserFetch.vue";
import UserEdit from "../views/UserEdit.vue";
import { createRouter, createWebHistory } from "vue-router";
import UserLogin from "../views/UserLogin.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "User",
      component: UserFetch,
    },
    {
      path: "/create",
      name: "Create",
      component: UserAdd,
    },
    {
      path: "/edit/:id",
      name: "Edit",
      component: UserEdit,
    },
    {
      path: "/login",
      name: "Login",
      component: UserLogin
    }
  ],
});

export default router;
