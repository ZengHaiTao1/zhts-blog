<template>
    <div :class="wowclass" data-wow-duration="2s" data-wow-delay="1s" class="BlogNotice">
        <div class="BlogNotice-title">
            <span class="iconfont icon-gonggaoS horn"></span>
            公告
        </div>
        <template v-if="notice">
            <div class="BlogNotice-description">{{notice.notice}}</div>
        </template>
        <template v-if="!notice">
            <div class="BlogNotice-description" style=" background: #f2f2f2;height:20px "></div>
        </template>
    </div>
</template>

<script>
import { getNotice } from "@/http/http.js";
export default {
    name: "",
    data() {
        return {
            wowclass: "wow fadeIn",
            notice: ""
        };
    },
    methods: {},
    mounted() {
        setTimeout(() => {
            this.wowclass = "";
        }, 4000);
    },
    created() {
        getNotice().then(res => {
            this.notice = res;
        });
    }
};
</script>

<style scoped lang = "scss">
.BlogNotice {
    margin-bottom: 20px;
    height: auto;
    /* background: rgb(98, 45, 12); */
    /* box-shadow: 0 4px 8px 6px rgba(7, 17, 27, 0.06); */
    box-shadow: 0px 0px 3px rgba(7, 17, 27, 0.2);
    border-radius: 8px;
    padding: 20px 24px;
    transition: all 0.3s;
    &:hover {
        /* box-shadow: 0 4px 12px 12px rgba(7, 17, 27, 0.15); */
        box-shadow: 0 0 1px black;
    }
    .BlogNotice-title {
        padding: 5px 0;
    }
    .BlogNotice-description {
        font-size: 14px;
        padding: 5px 0;
    }
}
</style>
