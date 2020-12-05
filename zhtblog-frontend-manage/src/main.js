import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/element-ui'

import '@/assets/css/zht.css'

//引入图标
import '@/assets/icon/iconfont.css'

Vue.prototype.$publicPath = process.env.BASE_URL

//定义第三方组件，实现非父子组件传值
Vue.prototype.bus = new Vue()

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

router.afterEach(() => {
    window.scrollTo(0, 0)
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
    NProgress.done()
})

//配置ECharts 
import ECharts from 'vue-echarts' // 在 webpack 环境下指向 components/ECharts.vue
import 'echarts/lib/chart/bar'
import 'echarts/lib/chart/pie'
import 'echarts/lib/component/tooltip'
import 'echarts/lib/component/polar'
import 'echarts/lib/component/legend'
import 'echarts/lib/component/title.js'


Vue.component('v-chart', ECharts)

//配置axios
import axios from 'axios'
const axiosInstance = axios.create({
    withCredentials: true,// 指定某个请求应该发送凭据。允许客户端携带跨域cookie，也需要此配置,
    baseURL: "/api"
    // baseURL: "http://49.234.222.203:8081/"
});
axiosInstance.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
axiosInstance.defaults.headers.get['Content-Type'] = 'application/json;charset=utf-8';

import { localStorage } from "@/utils/Storage.js"
// let storage = window.localStorage;
console.log(localStorage)

// localStorage.removeItem("zhtblog_token")
console.log(localStorage.getExpire("zhtblog_token"))

window.axiosInstance = axiosInstance

import { token } from "@/http/http.js"
if (localStorage.getExpire("zhtblog_token")) {
    token().then(res => {
        console.log(res)
        if (!res) {
            router.push({ path: "/login" })
        }
        if (res.status == "200") {
            window.axiosInstance.defaults.headers.common['token'] = localStorage.getExpire("zhtblog_token");
        } else {
            console.log(312)
            router.push({ path: "/login" })
        }

    }).catch(() => {
    });
}

console.log(localStorage.getExpire("zhtblog_token"))
router.beforeEach((to, _from, next) => {
    console.log(localStorage.getExpire("zhtblog_token"))
    NProgress.start();
    if (to.path === '/login') {
        next();
    } else {
        if (store.getters.getLogin || window.localStorage.getExpire("zhtblog_token")) {
            next();
        } else {
            Vue.prototype.$message.error('请先登录');
            next('/login');
        }
    }
    next()
});

Vue.config.productionTip = false
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
