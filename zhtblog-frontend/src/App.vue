<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>
export default {
       metaInfo: {
        title: "zht's blog|曾海涛的博客", // set a title
        meta: [
            {
                name: "keyWords",
                content: "前端，计算机技术，javascript,webpack,es6"
            },
            {
                name: "author",
                content: "zht"
            },
            {
                name: "description",
                content:
                    "zht's blog 是一个分享个人所学的技术博客，也会记录一些日常信息。"
            }
        ]
    },
    name: "",
    //  data() {},
    // methods: {},
    components: {},
    // beforeRouteEnter(to, from, next) {
    //     console.log(to);
    // },
    mounted() {}
};
</script>
<style lang="scss">
// body{
//     // background: #e1f0f8
// }
</style>>