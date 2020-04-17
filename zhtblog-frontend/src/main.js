import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index'

//按需要引入UI
import { Tag } from 'element-ui';
Vue.use(Tag)
//引入公共css
import "@/assets/css/zht.css"
import "@/assets/css/animate.css"
import "@/assets/css/index.css"
import "@/assets/icon/iconfont.css"


//配置子路径
Vue.prototype.$publicPath = process.env.BASE_URL

//配置中间组件
Vue.prototype.bus = new Vue()

//图片懒加载vue-lazyload
import VueLazyLoad from 'vue-lazyload'
Vue.use(VueLazyLoad, {
    error: process.env.BASE_URL + 'lazyimg/404.jpg',
    loading: process.env.BASE_URL + 'lazyimg/loading.gif'
})

//导入axios
import axios from 'axios';
// 每次请求携带cookies信息
const axiosInstance = axios.create({
    withCredentials: true,// 指定某个请求应该发送凭据。允许客户端携带跨域cookie，也需要此配置,
    baseURL: "/api"
    // baseURL: "http://49.234.222.203:8081/"

});
axiosInstance.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
axiosInstance.defaults.headers.get['Content-Type'] = 'application/json;charset=utf-8';
Vue.prototype.axios = axios
window.axiosInstance = axiosInstance

//引入代码高亮样式
// import 'highlight.js/styles/atom-one-dark.css'
import 'highlight.js/styles/github.css'
// import 'highlight.js/styles/zenburn.css'


//公共组件，使用方法 this.pul.xxx

//配置加载进度条
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
NProgress.configure({
    easing: 'ease',  // 动画方式    
    speed: 500,  // 递增进度条的速度    
    showSpinner: false, // 是否显示加载ico    
    trickleSpeed: 200, // 自动递增间隔    
    minimum: 0.3 // 初始化时的最小百分比
})
router.beforeEach((_to, _from, next) => {
    // 每次切换页面时，调用进度条
    NProgress.start();
    // 这个一定要加，没有next()页面不会跳转的。这部分还不清楚的去翻一下官网就明白了
    // setTimeout(() => {
    //   next();
    // }, 1000)
    next()
});

import MetaInfo from 'vue-meta-info';
Vue.use(MetaInfo)

router.afterEach((_to, _from) => {
    // if (to.fullPath == "/") {
    //   NProgress.done()
    //   return;
    // }
    // window.scrollTo(0, 0)
    // document.body.scrollTop = 0;
    // document.documentElement.scrollTop = 0;
    NProgress.done()
})

Vue.config.productionTip = false
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
