<template>
    <div class="Dashboard">
        <StatisticsArticles></StatisticsArticles>
        <Statisticscategory></Statisticscategory>
        <keep-alive>
            <Accesslog :key="this.$route.fullPath"></Accesslog>
        </keep-alive>
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
        StatisticsArticles: () =>
            import(
                "@/views/RightContent/Dashboard/StatisticsArticles/StatisticsArticles.vue"
            ),
        Statisticscategory: () =>
            import(
                "@/views/RightContent/Dashboard/Statisticscategory/Statisticscategory.vue"
            ),
        Accesslog: () =>
            import("@/views/RightContent/Dashboard/Accesslog/Accesslog.vue")
    }
};
</script>

<style scoped lang="scss">
.Dashboard {
    padding: 20px 30px;
}
</style>
