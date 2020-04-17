import lable from './model/lable'
import article from './model/article'
import Theme from './model/Theme'
import categories from './model/categories'
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = new Vuex.Store({
    modules: {
        lable: Object.assign({}, {
            namespaced: true,
        }, lable),
        article: Object.assign({}, {
            namespaced: true,
        }, article),
        Theme: Object.assign({}, {
            namespaced: true,
        }, Theme),
        categories: Object.assign({}, {
            namespaced: true,
        }, categories),
    }
})
export default store