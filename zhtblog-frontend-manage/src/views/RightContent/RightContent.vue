<template>
    <div class="RightContent" :style="'margin-left:'+ml+'px'">
        <Top></Top>
        <!-- <MavonEditor></MavonEditor> -->
        <!-- <Tags></Tags> -->
        <!-- <Allarticles></Allarticles> -->
        <!-- <draftsArticles></draftsArticles> -->
        <!-- <Dashboard></Dashboard> -->
        <transition name="fade" mode="out-in">
            <!-- <keep-alive> -->
            <router-view :key="this.$route.fullPath"></router-view>
            <!-- </keep-alive> -->
        </transition>
    </div>
</template>

<script>
export default {
    name: "",
    data() {
        return {
            ml: 210
        };
    },
    methods: {},
    components: {
        // MavonEditor: () =>
        //     import("@/views/RightContent/Editor/MavonEditor.vue"),
        // Notice: () => import("@/views/RightContent/Notice/Notice.vue")
        // Tags: () => import("@/views/RightContent/Tags/Tags.vue")
        // Allarticles: () =>
        //     import("@/views/RightContent/Allarticles/Allarticles.vue")
        // draftsArticles: () =>
        //     import("@/views/RightContent/draftsArticles/draftsArticles.vue")
        // Dashboard: () => import("@/views/RightContent/Dashboard/Dashboard.vue"),
        Top: () => import("@/views/RightContent/Top/Top.vue")
    },
    mounted() {
        this.bus.$on("update", () => {
            if (this.ml === 210) {
                this.ml = 70;
            } else {
                this.ml = 210;
            }
        });
    }
};
</script>

<style scoped lang = "scss">
.RightContent {
    min-height: 300px;
    height: 100vh;
    transition: all 0.28s ease-in-out;
}
.fade-enter-active,
.fade-leave-active {
    transition: all 0.6s;
}
.fade-enter/* .fade-leave-active below version 2.1.8 */ {
    opacity: 0;
    transform: translateX(-30px);
}
.fade-enter-to {
    opacity: 1;
    transform: translateX(0px);
}
.fade-leave {
    opacity: 1;
    transform: translateX(0px);
}
.fade-leave-to {
    opacity: 0;
    transform: translateX(30px);
}
</style>
