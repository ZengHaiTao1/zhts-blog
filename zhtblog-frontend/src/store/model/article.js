import { getArticlsById, getArticlelistByPage } from "@/http/http.js"
import axios from "axios"
const state = {
    articeList: [],

}
const getters = {
    // ArticeCatalog: state => {
    //     return state.ArticeCatalog
    // }
}
const mutations = {

    // setArticeCatalog(state, data) {
    //     state.ArticeCatalog = data
    // },
}
const actions = {
    async getArticlelistByPage({ state }, page) {
        // commit("setArticeList", data)
        if (page == 1) {
            return axios.all([getArticlelistByPage(page), getArticlsById(26)]).then(axios.spread((owe, two) => {
                two.description = two.html.replace(/<.*?>/g, "").substring(0, 50) + "...";
                two.top = true
                console.log(two)
                owe.unshift(two);
                state.articeList = owe
                return owe
            }))
        }

        return getArticlelistByPage(page).then(res => {
            state.articeList = res
            return res
        })
    },
    async getArticeListById({ state }, id) {
        let data = null;
        const articeList = state.articeList;
        for (let i = 0; i < articeList.length; i++) {
            if (articeList[i].id === id) {
                data = articeList[i];
            }
        }
        if (!data) {
            let promise = getArticlsById(id)
            return promise
        }
        return data;

    },
}
const article = {
    state: state,
    getters: getters,
    mutations: mutations,
    actions: actions
}
export default article




