<template>
    <div class="LeftNavContent">
        <EleNav></EleNav>
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
        EleNav: () => import("@/views/LeftNav/EleNav/EleNav.vue")
    }
}   ;
</script>           

<style scoped lang="scss">
</style>
