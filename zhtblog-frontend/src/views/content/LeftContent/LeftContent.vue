<template>
    <div class="LeftContent">
        <template v-if="articleData">
            <div class="img-wrapper visible-l visible-xl visible-xxl">
                <img
                    style="width:100%;height:250px"
                    alt
                    v-lazy="'http://cdn.zhtblog.cn/banner.jpg'"
                />
                <!-- v-lazy="'http://oss.dblearn.cn/dbblog/20190303/18a6c1d2ed47494396462901ffe02f30.jpg'" -->
            </div>

            <template v-for="(item,index) in articleData">
                <keep-alive :key="index">
                    <MyArticle ref="art" :article="item" @wowint="Receive($event)"></MyArticle>
                </keep-alive>
            </template>

            <div class="more" :class="loding?'loading':''">
                <a @click="loadmore">
                    <p class="text" v-show="!loding">{{moreText}}</p>
                    <div class="typing_loader" v-show="loding"></div>
                </a>
            </div>
        </template>

        <template v-if="!articleData">
            <div class="img-wrapper visible-l visible-xl visible-xxl">
                <img style=" background: #f2f2f2;height:200px;width:100% " alt />
            </div>
            <MyArticle ref="art" :article="''"></MyArticle>
            <div class="more" style="border:0">
                <a style=" background: #f2f2f2;height:40px; ">
                    <p class="text"></p>
                </a>
            </div>
        </template>
    </div>
</template>

<script>
import { mapActions } from "vuex";
import { WOW } from "wowjs";
export default {
    name: "",
    data() {
        return {
            wow: "",
            wowclass: ["wow fadeIn", "wow slideInLeft"],
            loding: false,
            moreText: "浏览更多",
            articleData: undefined,
            page: 1,
            topArticle: {}
        };
    },
    methods: {
        ...mapActions("article", ["getArticlelistByPage", "getArticeListById"]),
        loadmore() {
            if (this.moreText === "暂无更多") {
                return;
            }
            for (let i = 0; i < this.$refs.art.length; i++) {
                this.$refs.art[i].wowclass = "";
            }
            new Promise(resolve => {
                this.loding = true;
                // setTimeout(() => {
                resolve();
                // }, 1000);
            }).then(() => {
                this.getArticlelistByPage(++this.page).then(res => {
                    let data = res;
                    if (data.length < 10) {
                        this.moreText = "暂无更多";
                        this.loding = false;
                    }
                    console.log(data);
                    for (let i = 0; i < data.length; i++) {
                        this.articleData.push(data[i]);
                    }
                    this.loding = false;
                });
            });
        },
        Receive() {
            this.wow.init();
        }
    },
    mounted() {},
    created() {
        this.getArticlelistByPage(1).then(res => {
            this.articleData = res;
        });
        this.wow = new WOW({
            live: false
        });
    },
    components: {
        MyArticle: () =>
            import("@/views/content/LeftContent/MyArticle/MyArticle.vue")
    },
    beforeRouteLeave(to, from, next) {
        console.log(to);
        //全局变量homeScroll默认为0 离开页面时 记录当前的页面滚动值
        this.$homeScroll =
            document.documentElement.scrollTop || document.body.scrollTop;
        //需要执行next函数 否则不起作用
        next();
    },
    activated() {
        //this.$nextTick()异步执行dom刷新
        this.$nextTick(() => {
            window.scrollTo(0, this.$homeScroll);
        });
    }
};
</script>

<style scoped lang=scss>
.LeftContent {
    margin-bottom: 20px;
    .img-wrapper {
        img[lazy="loading"] {
            opacity: 0;
            width: 10px;
            height: 10px;
        }
        img[lazy="loaded"] {
            opacity: 1;
            transition: opacity 1s ease-in;
        }
    }
}
.loading {
    border: 1px solid transparent !important;
}
.more {
    width: 110px;
    padding: 5px;
    margin: 10px auto;
    border: 1px solid #409eff;
    border-radius: 4px;
    cursor: pointer;
    a {
        display: block;
        position: relative;
        width: 110px;
        height: 30px;
        line-height: 30px;
        text-align: center;
        p {
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            text-align: center;
            font-size: 18px;
            color: #409eff;
        }
    }
}
.typing_loader {
    width: 6px;
    height: 6px;
    border-radius: 50%;
    animation: typing 1s linear infinite alternate;
    position: absolute;
    left: 40%;
    top: 50%;
    transform: translate(-50%, -50%);
    box-sizing: border-box;
}
@keyframes typing {
    0% {
        background-color: rgba(64, 158, 255, 1);
        box-shadow: 12px 0px 0px 0px rgba(64, 158, 255, 0.2),
            24px 0px 0px 0px rgba(64, 158, 255, 0.2);
    }
    25% {
        background-color: rgba(64, 158, 255, 0.4);
        box-shadow: 12px 0px 0px 0px rgba(64, 158, 255, 2),
            24px 0px 0px 0px rgba(64, 158, 255, 0.2);
    }
    75% {
        background-color: rgba(64, 158, 255, 0.4);
        box-shadow: 12px 0px 0px 0px rgba(64, 158, 255, 0.2),
            24px 0px 0px 0px rgba(64, 158, 255, 1);
    }
}
</style>
