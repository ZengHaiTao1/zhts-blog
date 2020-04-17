<template>
    <div class="MinMyHeader visible-m">
        <div class="Header sliding" :class="slide? 'slideDown':'slideUP'">
            <!-- <a href="#" class="logo"></a> -->
            <router-link to="/" tag="a" class="imghref">
                <img class="logo" v-lazy="'http://cdn.zhtblog.cn/logo.png'" />
            </router-link>
            <a @click="slid" class="icon-box">
                <span class="iconfont icon-caidan"></span>
            </a>
        </div>
        <div class="side-bar" :class="show?'show':''" @scroll.prevent>
            <div class="main-area" @click="slid">
                <div class="box">
                    <img class="navlogo" v-lazy="'http://cdn.zhtblog.cn/logo.png'" />
                    <div class="link-list-box">
                        <router-link class="link-list" tag="a" to="/tags">
                            <span class="iconfont icon-biaoqian"></span>标签
                        </router-link>
                        <router-link class="link-list" tag="a" to="/categories">
                            <span class="iconfont icon-fenlei"></span>
                            分类
                        </router-link>
                        <router-link class="link-list" tag="a" to="/archives">
                            <span class="iconfont icon-changyongtubiao-xianxingdaochu-zhuanqu-"></span>
                            时光轴
                        </router-link>
                    </div>
                </div>
            </div>
            <div class="mask" @click="slid"></div>
        </div>
    </div>
</template>

<script>
import { throttle } from "@/utils/utils.js";
export default {
    name: "",
    data() {
        return {
            height: 0,
            show: false,
            slide: true
        };
    },
    methods: {
        slid() {
            if (this.show === false) {
                this.show = true;
                document.addEventListener("touchmove", this.xxscroll, {
                    passive: false
                });
                document.body.parentNode.style.overflow = "hidden";
            } else {
                this.show = false;
                document.removeEventListener("touchmove", this.xxscroll, {
                    passive: false
                });
                document.body.parentNode.style.overflow = "auto";
            }
        },
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
        },
        xxscroll(e) {
            e.preventDefault();
            e.stopPropagation();
        }
    },
    created() {
        this.listenerFunction();
    }
};
</script>

<style scoped lang = "scss">
.sliding {
    transition: transform 0.8s;
}
.sliding.slideDown {
    transform: translateY(0);
}
.sliding.slideUP {
    transform: translateY(-60px);
}
.MinMyHeader {
    /* transform: translateY(0); */
    .Header {
        padding: 10px 25px;
        box-shadow: 0 0 1px rgba(0, 0, 0, 0.25);
        height: 40px;
        position: fixed;
        top: 0;
        z-index: 999;
        width: 100%;
        background: #fff;
        .icon-box {
            display: inline-block;
            line-height: 40px;
            font-size: 30px;
            width: 40px;
            cursor: pointer;
            position: absolute;
            right: 60px;
            z-index: 999;
            .iconfont {
                line-height: 40px;
                font-size: 30px;
            }
        }
        .logo {
            height: 100%;
            line-height: 40px;
            margin-bottom: 20px;
        }
    }
    .show {
        transform: translate(0) !important;
    }
    .side-bar {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: transparent;
        border-left: none;
        box-shadow: none;
        transition: all 0.4s cubic-bezier(0.4, 0, 0, 1) 0.1s;
        transform: translate(-100%);
        overflow: hidden;
        z-index: 9999;
        .main-area {
            height: 100%;
            background: white;
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            width: 290px;
            bottom: 0;
            background: #fff;
            overflow-x: hidden;
            overflow-y: auto;
            .box {
                height: auto;
                text-align: center;
                .navlogo {
                    display: inline-block;
                    width: 80%;
                    height: 50px;
                    padding: 20px 0;
                }
                .link-list {
                    border: 10px solid transparent;
                    display: block;
                    text-align: center;
                    font-size: 20px;
                    color: #333;
                    box-shadow: 0px 0px 5px #333;
                    &:hover {
                        color: #409eff;
                    }
                    .iconfont {
                        margin-right: 3px;
                    }
                }
            }
        }

        .mask {
            width: 100%;
            height: 100%;
            transition: all 0.2s ease-in-out;
            background: rgba(85, 85, 85, 0.5);
        }
    }
}
</style>
