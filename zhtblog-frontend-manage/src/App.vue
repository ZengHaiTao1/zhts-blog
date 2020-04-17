<template>
    <div id="app">
        <!-- <index></index> -->
        <!-- <LeftNavContent></LeftNavContent>
        <RightContent></RightContent>-->
        <router-view></router-view>
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
        // LeftNavContent: () => import("@/views/LeftNav/LeftNavContent.vue"),
        // RightContent: () => import("@/views/RightContent/RightContent.vue")
        // index: () => import("@/views/index.vue")
    }
};
</script>

<style scoped>
</style>
