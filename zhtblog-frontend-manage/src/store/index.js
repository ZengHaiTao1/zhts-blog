import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

import {
  getArticlsByPage,
  getArticlsById
} from "@/http/http.js";
export default new Vuex.Store({
  state: {
    articlNum: "",
    articList: [],
    login: false
  },
  getters: {
    getArticlNum: state => {
      return state.articlNum
    },
    getLogin: state => {
      return state.login
    }
  },
  mutations: {
    setArticlNum(state, num) {
      state.articlNum = num
    },
    setArticList(state, data) {
      state.articlNum = data
    },
    setLogin(state, data) {
      state.login = data
    },
  },
  actions: {
    async getArticlsByPage({ state }, page) {
      return getArticlsByPage(page).then(res => {
        state.articList = res
        // console.log(res)
        return res
      })
    },
    async getArticeListById({ state }, id) {
      let data = null;
      const articeList = state.articList;
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

  },
  modules: {
  }
})
