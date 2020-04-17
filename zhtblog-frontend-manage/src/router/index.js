import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/admin',
    component: () => import('@/views/index.vue'),
    children: [
      {
        path: "/admin",
        component: () => import('@/views/RightContent/Dashboard/Dashboard.vue'),
      },
      {
        path: "/admin/Allarticles/:id",
        component: () => import('@/views/RightContent/Allarticles/Allarticles.vue'),
      },
      {
        path: "/admin/Editor",
        component: () => import('@/views/RightContent/Editor/MavonEditor.vue'),
      },
      {
        path: "/admin/setArticle/:id",
        component: () => import('@/views/RightContent/Editor/MavonEditor.vue'),
      },
      {
        path: "/admin/setDraftsArticle/:id",
        component: () => import('@/views/RightContent/Editor/MavonEditor.vue'),
      },
      {
        path: "/admin/draftsArticles",
        component: () => import('@/views/RightContent/draftsArticles/draftsArticles.vue'),
      },
      {
        path: "/admin/Notice",
        component: () => import('@/views/RightContent/Notice/Notice.vue'),
      },
      {
        path: "/admin/Tags",
        component: () => import('@/views/RightContent/Tags/Tags.vue'),
      },
      {
        path: "/admin/category",
        component: () => import('@/views/RightContent/Tags/Tags.vue'),
      },
    ]
  }
  ,
  {
    path: "/login",
    component: () => import('@/views/Login/Login.vue'),
  }
]

const router = new VueRouter({
  mode: "history",
  routes,
  base: '/admin/',

})

export default router
