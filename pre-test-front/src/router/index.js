import UserForm from '../components/UserForm.vue'
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
      path:"/user/form",
      name: "Form",
      component: UserForm
    }
  ],
})

export default router
