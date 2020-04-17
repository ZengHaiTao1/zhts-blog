<template>
    <article
        :class="wowclass"
        data-wow-duration="1.2s"
        data-wow-delay="0.1s"
        v-if="article.id!=26||article.top"
    >
        <template>
            <h4 class="article-title">
                <router-link :to="'/post/'+article.id">{{article.title}}</router-link>
                <span v-if="article.top" title="置顶" class="special">置顶</span>
            </h4>
            <div class="article-tags">
                <template v-for="(item,index) in article.tags">
                    <a :key="index">
                        <el-tag
                            :type="tagtype[getRandomInt(0,3)]"
                            size="small"
                            :key="index"
                        >{{item}}</el-tag>
                    </a>
                </template>
            </div>
            <p class="article-description">
                {{article.description}}
                <router-link :to="'/post/'+article.id">
                    查看更多
                    <span class="iconfont icon-youjiantou" style="margin-right:2px;"></span>
                </router-link>
            </p>
            <p class="artice-information">
                <span class="publish-time">
                    At time /
                    <span
                        class="iconfont icon-shijian"
                        style="margin-right:2px;color: #2d8cf0"
                    ></span>
                    <a>{{article.time}}</a>
                </span>
                <span class="readings">
                    <span class="iconfont icon-liulan" style="margin-right:2px;margin-left:10px"></span>
                    <a>{{article.browse}} 阅读</a>
                </span>
                <span class="likes">
                    <span class="iconfont icon-like" style="margin-right:4px;margin-left:6px;"></span>
                    <a>{{article.likearticls}} 喜欢</a>
                </span>
            </p>
        </template>
        <template v-if="!article">
            <h4 class="article-title" style=" background: #f2f2f2;height:20px ">
                <a style=" background: #f2f2f2;height:20px "></a>
            </h4>
            <div class="article-tags">
                <a>
                    <el-tag :type="tagtype[getRandomInt(0,3)]" size="small"></el-tag>
                </a>
                <a>
                    <el-tag :type="tagtype[getRandomInt(0,3)]" size="small"></el-tag>
                </a>
                <a>
                    <el-tag :type="tagtype[getRandomInt(0,3)]" size="small"></el-tag>
                </a>
            </div>
            <p class="article-description">
                <a>
                    查看更多
                    <span class="iconfont icon-youjiantou" style="margin-right:2px;"></span>
                </a>
            </p>
            <p class="artice-information">
                <span class="publish-time">
                    At time /
                    <span
                        class="iconfont icon-shijian"
                        style="margin-right:2px;color: #2d8cf0"
                    ></span>
                    <a></a>
                </span>
                <span class="readings">
                    <span class="iconfont icon-liulan" style="margin-right:2px;margin-left:10px"></span>
                    <a>阅读</a>
                </span>
                <span class="likes">
                    <span class="iconfont icon-like" style="margin-right:4px;margin-left:6px;"></span>
                    <a>喜欢</a>
                </span>
            </p>
        </template>
    </article>
</template>

<script>
// import { WOW } from "wowjs"
export default {
    name: "",
    data() {
        return {
            wowclass: "wow slideInLeft", //fadeIn  slideInLeft
            tagtype: ["success", "info", "warning", "danger"]
        };
    },
    props: ["article"],
    methods: {
        getRandomInt(min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        }
    },
    // watch: {
    //     article() {
    //         console.log(111);
    //         this.$nextTick(() => {
    //             // 在dom渲染完后,再执行动画
    //             var wow = new WOW({
    //                 live: false
    //             });
    //             wow.init();
    //         });
    //     }
    // },
    // created() {
    //     var wow = new WOW({
    //         live: false
    //     });
    //     wow.init();
    // },
    created() {
        this.$emit("wowint", "init了");
    }
};
</script>

<style scoped lang="scss">
.LeftContent {
    /* background: rgb(166, 77, 88); */
    .img-wrapper {
        img {
            width: 100%;
        }
    }
    article {
        margin-top: 15px;
        padding: 20px;
        border: 1px solid #eee;
        box-shadow: 2px 2px 3px #eee;
        &:hover {
            box-shadow: 0 0 1px black;
            .article-description {
                a {
                    color: #2d8cf0;
                    background: 0 0;
                    outline: 0;
                    cursor: pointer;
                    transition: color 0.2s ease;
                    text-decoration: underline;
                }
            }
        }
        .article-title {
            font-size: 23px;
            font-weight: 100;
            line-height: 27px;
            // white-space: nowrap;
            // text-overflow: ellipsis;
            // overflow: hidden;
            word-break: break-all;
            a {
                color: #000;
                &:hover {
                    color: #2d8cf0;
                    background: 0 0;
                    outline: 0;
                    cursor: pointer;
                    transition: color 0.2s ease;
                    text-decoration: underline;
                }
            }
            .special {
                border-radius: 4px;
                font-size: 12px;
                font-weight: 100;
                padding: 3px 5px;
                margin-left: 3px;
                vertical-align: top;
                color: #fff;
                background: salmon;
                cursor: pointer;
            }
        }
        .article-tags {
            margin: 8px 0;
            .el-tag {
                margin-right: 4px;
            }
        }
        .article-description {
            color: #666;
            font-size: 14px;
            line-height: 20px;
            font-weight: 200;
            // white-space: nowrap;
            // text-overflow: ellipsis;
            // overflow: hidden;
            word-break: break-all;
            a {
                color: #409eff;
                font-weight: 500;
                cursor: pointer;
                &:hover {
                    color: #2d8cf0;
                    background: 0 0;
                    outline: 0;
                    cursor: pointer;
                    transition: color 0.2s ease;
                    text-decoration: underline;
                }
            }
        }
        .artice-information {
            font-size: 14px;
            margin: 15px 0 0;
            span {
                margin-right: 8px;
                color: #7e8c8d;
                a {
                    cursor: pointer;
                    color: #7e8c8d;
                    &:hover {
                        color: #2d8cf0;
                        background: 0 0;
                        outline: 0;
                        cursor: pointer;
                        transition: color 0.2s ease;
                        text-decoration: underline;
                    }
                }
            }
        }
    }
}
</style>
