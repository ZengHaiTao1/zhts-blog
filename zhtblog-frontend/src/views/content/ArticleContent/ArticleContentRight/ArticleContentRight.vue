<template>
    <div class="ArticleContentRight">
        <RecommendedReading></RecommendedReading>
        <ArticeCatalog></ArticeCatalog>
    </div>
</template>

<script>
export default {
    name: "",
    data() {
        return {};
    },
    methods: {},
    components: {
        RecommendedReading: () =>
            import(
                "@/views/content/RightAside/RecommendedReading/RecommendedReading.vue"
            ),
        ArticeCatalog: () =>
            import(
                "@/views/content/ArticleContent/ArticleContentRight/ArticeCatalog/ArticeCatalog.vue"
            )
    }
};
</script>

<style scoped lang="scss">
.ArticleContentRight {
    height: auto;
    .RecommendedReading {
        margin-top: 0;
    }
}
</style>
