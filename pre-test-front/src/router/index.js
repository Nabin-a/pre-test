import UserInfo from '../components/UserInfo.vue'
import UserFetch from '../views/UserFetch.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/user",
      name: "User",
      component: UserFetch
    },
  ],
})

export default router
