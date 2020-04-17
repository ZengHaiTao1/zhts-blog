<template>
    <main class="clearfix">
        <div class="col-xxl-9 col-xl-9 col-x-12 col-m-12 div1">
            <!-- <ArticleContentLeft></ArticleContentLeft> -->
            <!-- <LeftContent></LeftContent> -->
            <keep-alive>
                <router-view name="LeftContent" :key="this.$route.path"></router-view>
            </keep-alive>

            <!-- <TimeAxisContent></TimeAxisContent> -->
            <!-- <TagContent></TagContent> -->
            <!-- <categoriesContent></categoriesContent> -->
        </div>
        <div class="col-xxl-3 col-xl-3 col-x-12 col-m-12 div2" ref="RightAside">
            <keep-alive>
                <router-view name="RightContent"></router-view>
            </keep-alive>
            <!-- <RightAside></RightAside> -->
            <!-- <ArticleContentRight></ArticleContentRight> -->
        </div>
    </main>
</template>

<script>
export default {
    name: "",
    data() {
        return {};
    },
    methods: {},
    components: {
        // LeftContent: () =>
        //     import("@/views/content/LeftContent/LeftContent.vue"),
        // RightAside: () => import("@/views/content/RightAside/RightAside.vue"),
        // ArticleContentLeft: () =>
        //     import(
        //         "@/views/content/ArticleContent/ArticleContentLeft/ArticleContentLeft.vue"
        //     ),
        // ArticleContentRight: () =>
        //     import(
        //         "@/views/content/ArticleContent/ArticleContentRight/ArticleContentRight.vue"
        //     ),
        // TimeAxisContent: () =>
        //     import("@/views/content/TimeAxisContent/TimeAxisContent.vue"),
        // TagContent: () => import("@/views/content/TagContent/TagContent.vue"),
        // categoriesContent: () =>
        //     import("@/views/content/categoriesContent/categoriesContent.vue")
    },
    mounted() {}
};
</script>

<style scoped lang="scss">
main {
    height: auto;
    width: auto;
    @media only screen and (max-width: 768px) {
        margin: 5px 5px 10px;
    }
    @media screen and (min-width: 768px) {
        // margin-top: 15px;
        margin: 10px 10px 20px;
    }
    @media screen and (min-width: 992px) {
        // margin-top: 15px;
        margin: 15px 35px 50px;
    }
    @media screen and (min-width: 1200px) {
        width: 1200px;
        margin: 15px auto 0;
        // margin: 15px 35px 50px;
    }
}
.div1 {
    position: relative;
    @media screen and (max-width: 768px) {
        // margin-top: 15px;
        padding: 0 7px;
    }
}
.div2 {
    @media screen and (max-width: 768px) {
        // margin-top: 15px;
        padding: 0 7px;
    }
}
</style>
