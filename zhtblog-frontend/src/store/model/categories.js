import { getCategoriesNameById as getCategories } from "@/http/http.js"
const state = {
    categorielist: [],
}
const getters = {
    // ArticeCatalog: state => {
    //     return state.ArticeCatalog
    // }
}
const mutations = {
    setCategorielist(state, data) {
        state.categorielist = data
    },
}
const actions = {
    async getCategoriesNameById({ state }, id) {
        let res = null;
        for (let i = 0; i < state.categorielist.length; i++) {
            let data = state.categorielist[i]
            if (data.id == id) {
                res = data
            }
        }
        if (res == null) {
            console.log("vuex中没有")
            return getCategories(id).then(res => {
                return res.data.data
            })
        }
        return res;
    }
}
const categories = {
    state: state,
    getters: getters,
    mutations: mutations,
    actions: actions
}
export default categories




