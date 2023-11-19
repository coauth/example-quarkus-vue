import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RegisterView from '../views/RegisterView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'register',
      component: RegisterView
    },
    {
      path: '/verify-register',
      name: 'verify-register',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/RegisterVerifyView.vue')
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/dashboard/DashboardView.vue'),
      children: [
        {
          path: '/dashboard',
          component: () => import('../views/dashboard/DashboardContainer.vue'),
        },
        {
          path: '/dashboard/transfer-step-one',
          component: () => import('../views/dashboard/TransferStartView.vue'),
        },
        {
          path: '/dashboard/delete-payee-step-one',
          component: () => import('../views/dashboard/DeletePayeeView.vue'),
        },
        {
          path: '/dashboard/transfer-step-two',
          component: () => import('../views/dashboard/TransferConfirmView.vue'),
        },
        {
          path: '/dashboard/transfer-step-three',
          component: () => import('../views/dashboard/TransferSuccessView.vue'),
        }
      ]
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    }
  ]
})

export default router
