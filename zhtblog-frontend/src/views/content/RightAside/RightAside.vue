<template>
    <aside class="RightAside">
        <!-- <BlogSummary></BlogSummary> -->
        <keep-alive>
            <router-view name="BlogSummary"></router-view>
        </keep-alive>
        <!-- <BlogNotice></BlogNotice> -->
        <keep-alive>
            <router-view name="BlogNotice"></router-view>
        </keep-alive>
        <keep-alive>
            <router-view name="RecommendedReading"></router-view>
        </keep-alive>
        <!-- <RecommendedReading></RecommendedReading> -->
        <keep-alive>
            <router-view name="Categories"></router-view>
        </keep-alive>
        <keep-alive>
            <router-view name="ArticeCatalog" :key="this.$route.path"></router-view>
        </keep-alive>
        <!-- <Categories></Categories> -->
        <keep-alive>
            <router-view name="BlogTag"></router-view>
        </keep-alive>
        <!-- <BlogTag></BlogTag> -->
        <keep-alive>
            <router-view name="Bloginfo"></router-view>
        </keep-alive>
        <!-- <Bloginfo></Bloginfo> -->
    </aside>
</template>

<script>
export default {
    name: "",
    data() {
        return {};
    },
    methods: {},
    components: {
        // BlogSummary: () =>
        //     import("@/views/content/RightAside/BlogSummary/BlogSummary.vue"),
        // BlogNotice: () =>
        //     import("@/views/content/RightAside/BlogNotice/BlogNotice.vue"),
        // RecommendedReading: () =>
        //     import(
        //         "@/views/content/RightAside/RecommendedReading/RecommendedReading.vue"
        //     ),
        // Categories: () =>
        //     import("@/views/content/RightAside/Categories/Categories.vue"),
        // BlogTag: () => import("@/views/content/RightAside/BlogTag/BlogTag.vue"),
        // Bloginfo: () =>
        //     import("@/views/content/RightAside/Bloginfo/Bloginfo.vue")
    }
};
</script>

<style scoped lang=scss>
/* .RightAside {
    /* background: rgb(16, 177, 188); 
} */
</style>
