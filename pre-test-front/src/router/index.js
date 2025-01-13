import UserAdd from '../views/UserAdd.vue'
import UserFetch from '../views/UserFetch.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "User",
      component: UserFetch
    },
    {
      path:"/create",
      name: "Create",
      component: UserAdd
    },
  ],
})

export default router
