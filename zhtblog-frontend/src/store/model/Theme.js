import * as myTheme from '../../Theme/Blue'
const blue = myTheme.blue
const state = {
    topBgcolor: blue.topBgcolor
}
const getters = {
    topBgcolor: state => {
        console.log(1212)
        console.log(state.topBgcolor)
        return state.topBgcolor
    }
}
const mutations = {
    color(state) {
        // 变更状态
        console.log("red1111")
        state.topBgcolor = "red"
    }
}
const actions = {
}
const Theme = {
    state: state,
    getters: getters,
    mutations: mutations,
    actions: actions
}
export default Theme