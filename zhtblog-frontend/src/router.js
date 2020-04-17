import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

//配置首页不进行懒加载，减少http请求
import LeftContent from "@/views/content/LeftContent/LeftContent.vue"
import RightAside from "@/views/content/RightAside/RightAside.vue"
import BlogTag from "@/views/content/RightAside/BlogTag/BlogTag.vue"
import BlogNotice from "@/views/content/RightAside/BlogNotice/BlogNotice.vue"
import BlogSummary from "@/views/content/RightAside/BlogSummary/BlogSummary.vue"
// import Bloginfo from "@/views/content/RightAside/Bloginfo/Bloginfo.vue"
import Categories from "@/views/content/RightAside/Categories/Categories.vue"
import RecommendedReading from "@/views/content/RightAside/RecommendedReading/RecommendedReading.vue"

export default new Router({
  mode: "history",
  scrollBehavior(_to, _from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  },
  routes: [
    {
      path: '/',
      // name: 'home',
      component: () => import("@/views/index.vue"),
      children: [
        {
          path: "/",
          components: {
            LeftContent: LeftContent,
            RightContent: RightAside,
          },
          children: [
            {
              path: "/",
              components: {
                BlogTag: BlogTag,
                BlogNotice: BlogNotice,
                BlogSummary: BlogSummary,
                // Bloginfo: Bloginfo,
                Categories: Categories,
                RecommendedReading: RecommendedReading,
              }
            }
          ]
        },
        {
          path: "/tags",
          components: {
            LeftContent: () => import("@/views/content/TagContent/TagContent.vue"),
            RightContent: RightAside,
          },
          children: [
            {
              path: "/tags",
              components: {
                BlogSummary: BlogSummary,
                Categories: Categories,
                RecommendedReading: RecommendedReading,
              }
            }
          ]

        },
        {
          path: "/categories",
          components: {
            LeftContent: () =>
              import("@/views/content/categoriesContent/categoriesContent.vue"),
            RightContent: RightAside,
          },
          children: [
            {
              path: "/categories",
              components: {
                BlogSummary: BlogSummary,
                RecommendedReading: RecommendedReading,
                BlogTag: BlogTag,
              }
            }
          ]
        },
        {
          path: "/archives",
          components: {
            LeftContent: () =>
              import("@/views/content/TimeAxisContent/TimeAxisContent.vue"),
            RightContent: RightAside,
          },
          children: [
            {
              path: "/archives",
              components: {
                BlogSummary: BlogSummary,
                RecommendedReading: RecommendedReading,
                BlogTag: BlogTag,
              }
            }
          ]
        },
        {
          path: "/tags/:id",
          components: {
            LeftContent: () =>
              import("@/views/content/TimeAxisContent/TimeAxisContent.vue"),
            RightContent: RightAside,
          },
          children: [
            {
              path: "/tags/:id",
              components: {
                BlogSummary: BlogSummary,
                RecommendedReading: RecommendedReading,
                BlogTag: BlogTag,
              }
            }
          ]
        },
        {
          path: "/categories/:id",
          components: {
            LeftContent: () =>
              import("@/views/content/TimeAxisContent/TimeAxisContent.vue"),
            RightContent: RightAside,
          },
          children: [
            {
              path: "/categories/:id",
              components: {
                BlogSummary: BlogSummary,
                RecommendedReading: RecommendedReading,
                BlogTag: BlogTag,
              }
            }
          ]
        },
        {
          path: "/post/:id",
          components: {
            LeftContent: () =>
              import("@/views/content/ArticleContent/ArticleContentLeft/ArticleContentLeft.vue"),
            RightContent: RightAside,
          },
          children: [
            {
              path: "/post/:id",
              components: {
                RecommendedReading: RecommendedReading,
                ArticeCatalog: () =>
                  import("@/views/content/ArticleContent/ArticleContentRight/ArticeCatalog/ArticeCatalog.vue"),
              }
            }
          ]
        }
      ]
      // redirect: '/',
      // children: [
      //   {
      //     path: '/index',
      //     components: {
      //       bodyleft: () => import("@/views/ZhtBodyleft.vue"),
      //       // imgloop: () => import("@/views/ZhtImgloop.vue"),
      //       // scrolldiv: () => import("@/views/ZhtScrolldiv.vue"),
      //       bodyright: () => import("@/views/ZhtBodyright.vue"),
      //     }
      //   },
      //   {
      //     path: '/articles/:id',
      //     name: "articles",
      //     components: {
      //       bodyleft: () => import("@/views/articles.vue"),
      //       crumbs: () => import("@/views/ZhtCrumbs.vue"),
      //       bodyright: () => import("@/views/ZhtBodyright2.vue"),
      //     }
      //   },
      //   {
      //     path: "/category/:id",
      //     name: "category",
      //     components: {
      //       bodyleft: () => import("@/views/ZhtBodyleft.vue"),
      //       bodyright: () => import("@/views/ZhtBodyright2.vue"),
      //     }
      //   }
      // ]
    },
    // {
    //   path: '/category/:name',
    //   name: 'category',
    //   component: () => import("@/views/ZhtBodyleft.vue")
    // },
    // {
    //   path: '/admin',
    //   name: 'admin',
    //   component: () => import("@/views/editor/wangeditor.vue")
    // },
    // {
    //   path: '*',
    //   redirect: '/index'
    // },

  ]
})
