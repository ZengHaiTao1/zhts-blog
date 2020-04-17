<template>
    <div class="main">
        <MyHeader></MyHeader>
        <MinMyHeader></MinMyHeader>
        <div style="height:60px;width:100%"></div>
        <ConTent></ConTent>
        <BackTop></BackTop>
        <myfooter></myfooter>
    </div>
</template>

<script>
import { addVisitor } from "@/http/http.js";
export default {
    name: "",

    data() {
        return {};
    },
    computed: {},
    created() {
        let data = {
            ip: returnCitySN["cip"],
            place: returnCitySN["cname"]
        };
        addVisitor(data);
        window.addEventListener("unload", this.closeHtml, false);
    },
    methods: {
        closeHtml() {
            let client = new XMLHttpRequest();
            client.open("POST", "/api/addVisitorLeave", false); // 第三个参数表明是同步的 xhr
            client.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
            client.send();
        }
    },
    components: {
        MyHeader: () => import("@/views/header/MyHeader.vue"),
        ConTent: () => import("@/views/content/ConTent.vue"),
        BackTop: () => import("@/views/Setup/BackTop/BackTop.vue"),
        MinMyHeader: () => import("@/views/header/MinMyHeader.vue"),
        myfooter: () => import("@/views/footer/myfooter.vue")
    }
};
</script>

<style lang="scss">
</style>
