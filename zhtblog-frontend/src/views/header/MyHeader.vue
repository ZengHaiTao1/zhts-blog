<template>
    <header
        :style="'background:'+this.topBgcolor"
        class="clearfix sliding visible-xxl visible-xl visible-l"
        :class="slide? 'slideDown':'slideUP'"
    >
        <router-link to="/" id="logo">
            <img v-lazy="'http://cdn.zhtblog.cn/logo.png'" />
            <!-- <span class="title">Zht's Blog</span> -->
            <!-- <span class="motto">success belongs to the persevering</span> -->
        </router-link>
        <nav>
            <ul>
                <!-- <li style="width:300px">标签</li> -->
                <li>
                    <router-link tag="a" to="/tags">
                        <span class="iconfont icon-biaoqian" style="margin-right: 4px;"></span>标签
                    </router-link>
                </li>
                <li>
                    <router-link tag="a" to="/categories">
                        <span
                            class="iconfont icon-shuangsechangyongtubiao-"
                            style="margin-right: 4px;"
                        ></span>分类
                    </router-link>
                </li>
                <li>
                    <router-link tag="a" to="/archives">
                        <span
                            class="iconfont icon-changyongtubiao-xianxingdaochu-zhuanqu-"
                            style="margin-right: 4px;"
                        ></span>时间轴
                    </router-link>
                </li>
            </ul>
        </nav>
    </header>
</template>

<script>
import { mapGetters } from "vuex";
import { throttle } from "@/utils/utils.js";
export default {
    name: "",
    data() {
        return {
            slide: true
        };
    },
    methods: {
        listenerFunction(e) {
            document.addEventListener("scroll", this.handleScroll(), true);
        },
        handleScroll() {
            let fn = throttle(() => {
                if (window.pageYOffset < 120) {
                    this.scroll = window.pageYOffset;
                    return;
                } else if (window.pageYOffset - this.scroll > 0) {
                    this.slide = false;
                    this.scroll = window.pageYOffset;
                } else if (window.pageYOffset - this.scroll < 0) {
                    this.slide = true;
                    this.scroll = window.pageYOffset;
                }
            }, 200);
            return fn;
        }
    },
    created() {
        this.listenerFunction();
    },
    beforeDestroy() {
        document.removeEventListener("scroll", this.this.handleScroll());
    },
    computed: {
        ...mapGetters("Theme", ["topBgcolor"])
    }
};
</script>

<style scoped lang="scss">
.sliding {
    transition: transform 0.8s;
}
.sliding.slideDown {
    transform: translateY(0);
}
.sliding.slideUP {
    transform: translateY(-60px);
}
header {
    // background: #eff7fb;
    // height: 200px;
    padding: 10px 60px;
    position: fixed;
    top: 0;
    z-index: 999;
    width: 100%;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.25);
    #logo {
        display: inline-block;
        font-size: 1.5em;
        line-height: 40px;
        color: #2c3e50;
        font-family: Helvetica Neue, Arial, sans-serif;
        font-weight: 500;
        img {
            vertical-align: middle;
            margin-right: 6px;
            width: 140px;
            height: 40px;
        }
        img[lazy="loading"] {
            opacity: 0;
        }
        img[lazy="loaded"] {
            opacity: 1;
            transition: opacity 1s ease-in;
        }
    }
    nav {
        display: inline-block;
        right: 150px;
        height: 40px;
        position: absolute;
        ul {
            list-style-type: none;
            height: 40px;
            line-height: 40px;
            li {
                display: inline-block;
                position: relative;
                margin: 0 30px;
                text-align: left;
                a {
                    // margin-left: 20px;
                    color: #2c3e50;
                    width: 100%;
                    &:hover {
                        color: #409eff;
                        &::after {
                            width: 120%;
                        }
                    }
                    &::after {
                        position: absolute;
                        bottom: 0;
                        left: 0;
                        z-index: -1;
                        width: 0;
                        height: 3px;
                        background-color: #80c8f8;
                        content: "";
                        -webkit-transition: all 0.3s ease-in-out;
                        -moz-transition: all 0.3s ease-in-out;
                        -o-transition: all 0.3s ease-in-out;
                        -ms-transition: all 0.3s ease-in-out;
                        transition: all 0.3s ease-in-out;
                    }
                }
            }
        }
    }
}
</style>
