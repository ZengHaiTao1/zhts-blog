<template>
    <div
        class="BackTop"
        title="回到顶部"
        :style="'display:'+display+';'+'opacity:'+opacity"
        @click="gotoTop"
    >
        <span class="iconfont icon-youjiantou"></span>
    </div>
</template>

<script>
import { changScrollTop, getScrollTop, debounce } from "@/utils/utils.js";
export default {
    name: "",
    data() {
        return {
            display: "none",
            opacity: 0
        };
    },
    methods: {
        addScroll() {
            let fn = debounce(() => {
                if (getScrollTop() > 800) {
                    this.display = "block";
                    this.opacity = 1;
                } else {
                    this.display = "none";
                    this.opacity = 0;
                }
            }, 500);
            return fn;
        },
        listenerFunction() {
            document.addEventListener("scroll", this.addScroll(), true);
        },
        gotoTop() {
            changScrollTop(0, 1000);
        }
    },
    mounted() {
        this.listenerFunction();
    }
};
</script>

<style lang ="scss" scoped>
.BackTop {
    width: 30px;
    height: 30px;
    background: #49b1f5;
    position: fixed;
    bottom: 40px;
    right: 5px;
    transform: rotate(-90deg);
    cursor: pointer;
    transition: all 1s ease;
    .iconfont {
        color: white;
        font-size: 30px;
    }
}
</style>
