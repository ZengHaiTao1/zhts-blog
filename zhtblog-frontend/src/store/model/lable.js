import { getTagNameById as getTagById } from '@/http/http.js'
const state = {
    taglist: []
}
const getters = {
    HotTags: state => {
        return state.HotTags
    }
}
const mutations = {
    setTaglist(state, data) {
        state.taglist = data
    },
}
const actions = {
    async getTagNameById({ state }, id) {
        let res = null;
        for (let i = 0; i < state.taglist.length; i++) {
            let data = state.taglist[i]
            if (data.id == id) {
                res = data
            }
        }
        if (res == null) {
            console.log("vuex中没有")
            return getTagById(id).then(res => {
                return res.data.data
            })
        }
        return res;
    },
}
const lable = {
    state: state,
    getters: getters,
    mutations: mutations,
    actions: actions
}
export default lable