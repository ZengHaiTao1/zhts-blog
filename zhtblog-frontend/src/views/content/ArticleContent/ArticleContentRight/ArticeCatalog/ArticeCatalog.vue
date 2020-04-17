<template>
    <div
        class="ArticeCatalog visible-xl visible-xxl"
        ref="ArticeCatalog"
        :style="'top:'+divTop+'px'"
        v-if="ArticeCatalog.length"
    >
        <div class="ArticeCatalog-title" ref="aaa">
            <span class="iconfont icon-biaoqian1"></span>
            目录
        </div>
        <div class="Catalog-list">
            <div class="active-indicator" :style="{top:nowscrollnum*liHeight+'px'}"></div>
            <!-- <ul class="menu-root">
                <li class="menu-root-li">
                    <a class="toc-link">跳槽背景跳槽背景跳槽背景跳槽背景跳槽背景</a>
                </li>
                <li class="menu-root-li">
                    <a class="toc-link">公司选取</a>
                </li>
                <li class="menu-root-li">
                    <a class="toc-link">技术准备</a>
                </li>
                <li class="menu-root-li">
                    <a class="toc-link">阿里面试过程</a>
                    <ul class="menu-second">
                        <li class="menu-second-list">
                            <a class="toc-link">一面跳槽背景跳槽背景跳槽背景跳槽背景跳槽背景</a>
                        </li>
                        <li class="menu-second-list">二面</li>
                        <li class="menu-second-list">三面</li>
                        <li class="menu-second-list">HR面</li>
                        <li class="menu-second-list">五面</li>
                        <li class="menu-second-list">后续</li>
                    </ul>
                </li>
                <li class="menu-root-li">总结</li>
            </ul>-->
            <ul class="menu-root" v-if="ArticeCatalog.length!=0">
                <template v-for="(item ,index) in ArticeCatalog">
                    <li
                        ref="li"
                        :data-num="item.ScrollTop"
                        :key="index"
                        class="menu-root-li"
                        @click.stop="changScrollTop(item.ScrollTop,300)"
                    >
                        <a
                            :class="item.index===nowscrollnum?'active':''"
                            class="toc-link"
                        >{{item.name}}</a>
                        <ul class="menu-second" v-if="item.second.length!=0">
                            <template v-for="(itemsed,index) in item.second">
                                <li
                                    :data-num="itemsed.ScrollTop"
                                    :key="index"
                                    class="menu-second-list"
                                    @click.stop="changScrollTop(itemsed.ScrollTop,500)"
                                >
                                    <a
                                        :class="itemsed.index===nowscrollnum?'active':''"
                                        class="toc-link"
                                    >{{itemsed.name}}</a>
                                </li>
                            </template>
                        </ul>
                    </li>
                </template>
            </ul>
        </div>
    </div>
</template>

<script>
import {
    getScrollTop,
    getOffset,
    changScrollTop,
    type,
    throttle,
    getDomHeight,
    debounce,
    getClientHeight
} from "@/utils/utils.js";
export default {
    name: "",
    data() {
        return {
            nowscrollnum: 0,
            scrollLen: [],
            liHeight: 34.4,
            fixed: false,
            divTop: "",
            eleOffsetTop: "",
            ArticeCatalog: [
                // {
                //     name: "12",
                //     ScrollTop: 0,
                //     index: 0,
                //     second: []
                // }
            ]
        };
    },
    methods: {
        changData() {
            //转变数据格式，按照距离由低到高排列，其中第一个应该从第一个元素的高度算起，而不是零，最后一个的末位置为无限大
            let CataloglistData = this.ArticeCatalog;
            let len = -1;
            let length = CataloglistData[0].ScrollTop;
            for (let i = 1; i < CataloglistData.length; i++) {
                let ScrollTop = CataloglistData[i].ScrollTop;
                let index = CataloglistData[i].index;
                this.scrollLen.push({
                    begin: length,
                    End: ScrollTop,
                    num: ++len
                });
                length = ScrollTop;
                if (type.isArray(CataloglistData[i].second)) {
                    let CataloglistDataSecond = CataloglistData[i].second;
                    for (let i = 0; i < CataloglistDataSecond.length; i++) {
                        let ScrollTop = CataloglistDataSecond[i].ScrollTop;
                        let index = CataloglistDataSecond[i].index;
                        this.scrollLen.push({
                            begin: length,
                            End: ScrollTop,
                            num: ++len
                        });
                        length = ScrollTop;
                    }
                }
            }
            this.scrollLen.push({
                begin: length,
                End: 999999999,
                num: ++len
            });
        },
        addScrollTop() {
            let fn = throttle(() => {
                let display;
                if (this.$refs.ArticeCatalog) {
                    const win = this.$refs.ArticeCatalog.ownerDocument
                        .defaultView;
                    display = win.getComputedStyle(
                        this.$refs.ArticeCatalog,
                        null
                    ).display;
                }
                if (display === "none") {
                    return;
                }
                //依赖转变的数据格式，添加滚动事件
                let scrollLen = this.scrollLen;
                for (let i = 0; i < scrollLen.length; i++) {
                    if (
                        scrollLen[i].begin < getScrollTop() + 20 &&
                        getScrollTop() + 20 < scrollLen[i].End
                    ) {
                        this.nowscrollnum = scrollLen[i].num;
                        return;
                    }
                }
            }, 500);
            return fn;
        },
        getEl() {
            let titleData = [];
            let myArticle = document.getElementsByClassName("myArticle")[0];
            if (myArticle == undefined) {
                return;
            }
            let h1Arr = myArticle.getElementsByTagName("h1");
            for (let i = 0; i < h1Arr.length; i++) {
                titleData.push({
                    name: h1Arr[i].innerHTML.split("</a>")[1].trim(),
                    ScrollTop: parseInt(getOffset(h1Arr[i]).top) - 100,
                    titletype: "h1",
                    second: []
                });
            }
            let h2Arr = myArticle.getElementsByTagName("h2");
            for (let i = 0; i < h2Arr.length; i++) {
                titleData.push({
                    name: h2Arr[i].innerHTML.split("</a>")[1].trim(),
                    ScrollTop: parseInt(getOffset(h2Arr[i]).top) - 100,
                    titletype: "h2",
                    second: []
                });
            }
            titleData.sort((a, b) => {
                return a.ScrollTop - b.ScrollTop;
            });
            let h1index = null;
            for (let i = 0; i < titleData.length; i++) {
                if (titleData[i].titletype === "h2") {
                    if (h1index === null) {
                        titleData[i - 1].second.push(titleData[i]);
                        h1index = i - 1;
                    } else {
                        titleData[h1index].second.push(titleData[i]);
                    }
                } else if (titleData[i].titletype === "h1") {
                    if (h1index != null) {
                        h1index = i;
                    }
                }
            }
            titleData = titleData.filter((value, index, arr) => {
                return value.titletype === "h1";
            });
            return titleData;
        },
        mythrottle() {
            //减低滚动事件执行频率
            throttle(this.addScrollTop);
        },
        listenerFunction() {
            document.addEventListener("scroll", this.addScrollTop(), true);
            document.addEventListener("scroll", this.addfixed(), true);
        },
        addfixed() {
            let fn = debounce(() => {
                let display;
                if (this.$refs.ArticeCatalog) {
                    const win = this.$refs.ArticeCatalog.ownerDocument
                        .defaultView;
                    display = win.getComputedStyle(
                        this.$refs.ArticeCatalog,
                        null
                    ).display;
                }

                if (display === "none") {
                    return;
                }
                if (this.nowscrollnum > 10) {
                    this.divTop =
                        getScrollTop() -
                        this.liHeight *
                            (this.nowscrollnum + this.scrollLen.length / 2 - 6);
                } else {
                    if (getScrollTop() > this.eleOffsetTop) {
                        // let top = getScrollTop() - this.eleOffsetTop;
                        // this.divTop = top + 100;
                        this.divTop = getScrollTop() - this.eleOffsetTop + 100;
                    } else {
                        this.divTop = 0;
                    }
                }
            }, 200);
            return fn;
        },
        getWindowHeight() {
            var windowHeight = 0;
            if (document.compatMode == "CSS1Compat") {
                windowHeight = document.documentElement.clientHeight;
            } else {
                windowHeight = document.body.clientHeight;
            }
            return windowHeight;
        }
    },
    mounted() {
        // this.$nextTick(() => {
        //     console.log(this.getWindowHeight());
        //     console.log(this.eleOffsetTop);
        // });
        // if (getDomHeight(this.$refs.li[0])) {
        //     this.liHeight = getDomHeight(this.$refs.li[0]);
        // }
        this.$nextTick(() => {
            console.log("---------------------");
            console.log(this.ArticeCatalog);
        });
    },
    activated() {
        //this.$nextTick()异步执行dom刷新
        // this.$nextTick(() => {
        //     this.eleOffsetTop = getOffset(this.$refs.ArticeCatalog).top;
        // });
        if (!this.$refs.ArticeCatalog) {
            return;
        }
        this.eleOffsetTop = getOffset(this.$refs.ArticeCatalog).top;
    },
    watch: {
        ArticeCatalog() {
            this.$nextTick(function() {
                if (this.ArticeCatalog.length == 0) {
                    return;
                }
                if (!this.$refs.ArticeCatalog) {
                    return;
                }
                this.eleOffsetTop = getOffset(this.$refs.ArticeCatalog).top;
                this.addfixed();
                this.changScrollTop = changScrollTop;
            });
        }
    },
    created() {
        this.bus.$on("update", () => {
            try {
                let data = this.getEl();
                if (data == null) {
                    return;
                }
                if (data.length == 0) {
                    return;
                }
                this.ArticeCatalog = data;
                this.changData();
                this.listenerFunction();
            } catch (e) {
                console.log(e);
            }
        });
        this.bus.$on("height", () => {
            if (!this.$refs.ArticeCatalog) {
                return;
            }
            this.eleOffsetTop = getOffset(this.$refs.ArticeCatalog).top;
        });
    },
    computed: {
        // ArticeCatalog() {
        //     return this.$store.getters["article/ArticeCatalog"];
        // }
    }
};
</script>

<style scoped lang="scss">
.ArticeCatalog {
    box-sizing: border-box;
    height: auto;
    margin-top: 20px;
    // box-shadow: 0 4px 8px 6px rgba(7, 17, 27, 0.06);
    box-shadow: 0px 0px 3px rgba(7, 17, 27, 0.2);
    border-radius: 8px;
    // padding: 20px 24px;
    transition: box-shadow 0.3s;
    position: relative;
    transition: top 0.3s ease-in-out;
    // width: 270px;
    &:hover {
        // box-shadow: 0 4px 12px 12px rgba(7, 17, 27, 0.15);
        box-shadow: 0 0 1px black;
    }
    .ArticeCatalog-title {
        text-align: center;
        font-size: 18px;
        margin-bottom: 10px;
        padding-top: 20px;
    }
    .Catalog-list {
        height: auto;
        // background: rgba(99, 33, 11, 0.2);
        position: relative;
        // padding: 0 35px 15px;
        margin-top: 10px;
        font-size: 14px;
        padding: 0 35px 15px;
        .menu-root {
            position: relative;
            z-index: 2;
            .menu-root-li {
                cursor: pointer;
                list-style-type: square;
                line-height: 35px;
                .active {
                    font-weight: 800;
                    color: #409eff;
                }
                .toc-link {
                    cursor: pointer;
                    display: block;
                    height: 100%;
                    width: 100%;
                    margin: 0;
                    padding: 0;
                    font-size: 16px;
                    white-space: nowrap;
                    text-overflow: ellipsis;
                    overflow: hidden;
                }
                .menu-second {
                    position: relative;
                    padding-left: 1em;
                    z-index: 2;
                    color: #363636;
                    .menu-second-list {
                        list-style-type: square;
                        line-height: 35px;
                        .active {
                            font-weight: 800;
                            color: #409eff;
                        }
                        .toc-link {
                            cursor: pointer;
                            display: block;
                            height: 100%;
                            width: 100%;
                            margin: 0;
                            padding: 0;
                            font-size: 16px;
                            white-space: nowrap;
                            text-overflow: ellipsis;
                            overflow: hidden;
                        }
                    }
                }
            }
        }
        .active-indicator {
            position: absolute;
            left: -1px;
            right: 0;
            background-color: #f9f9f9;
            border-left: 2px solid #409eff;
            z-index: 1;
            -webkit-transition: all 0.2s ease-out;
            transition: all 0.2s ease-out;
            top: 0px;
            height: 34px;
        }
    }
}
</style>
