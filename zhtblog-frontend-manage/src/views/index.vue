<template>
    <div class="index">
        <LeftNavContent></LeftNavContent>
        <RightContent></RightContent>
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
        LeftNavContent: () => import("@/views/LeftNav/LeftNavContent.vue"),
        RightContent: () => import("@/views/RightContent/RightContent.vue")
    }
};
</script>

<style scoped>

</style>
