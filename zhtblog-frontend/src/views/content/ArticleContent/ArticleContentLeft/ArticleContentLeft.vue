<template>
    <div class="ArticleContent">
        <template v-if="article">
            <h1 class="article-title">{{article.title}}</h1>
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
            <div class="information-box clearfix">
                <div class="col-xxl-6 leftbox">
                    <p class="authorAndtime">
                        <span class="author">
                            By /
                            <a>zht</a>
                        </span>
                        <span class="publish-time">
                            At time /
                            <span
                                class="iconfont icon-shijian"
                                style="margin-right:2px;color: #2d8cf0"
                            ></span>
                            <a>{{article.time}}</a>
                        </span>
                    </p>
                </div>
                <div class="col-xxl-6 rightbox">
                    <p class="readAndlikes">
                        <span class="readings">
                            <span
                                class="iconfont icon-liulan"
                                style="margin-right:2px;margin-left:10px"
                            ></span>
                            <a>{{article.browse}} 阅读</a>
                        </span>
                        <span class="likes">
                            <span
                                class="iconfont icon-like"
                                :class="likeed?'likeed':''"
                                style="margin-right:4px;margin-left:6px;"
                            ></span>
                            <a @click="like" :class="likeed?'hover':''">{{article.likearticls}} 喜欢</a>
                        </span>
                    </p>
                </div>
            </div>
            <article>
                <keep-alive>
                    <MyArticle :html="article.html" :key="this.$route.path"></MyArticle>
                </keep-alive>
            </article>
            <div class="bottom-like">
                <span
                    @click="like"
                    class="iconfont icon-like"
                    :class="likeed?'likeed':''"
                    style="margin-right:4px;margin-left:6px;"
                ></span>
            </div>
            <div class="post-copyright">
                <div class="post-author">
                    <strong style="font-weight: bold !important;">文章作者 :</strong>
                    <router-link to="/">zht</router-link>
                </div>
                <div class="post-author">
                    <strong style="font-weight: bold !important;">文章链接 :</strong>
                    <a href="#">{{href}}</a>
                </div>
                <div class="post-copyright-meta">
                    <strong style="font-weight: bold !important;">版权声明 :</strong>
                    <span>
                        本博客所有文章除特别声明外，均采用
                        <a
                            href="https://creativecommons.org/licenses/by-nc-sa/4.0/"
                        >CC BY-NC-SA 4.0</a> 许可协议。转载请注明来自
                        <router-link to="/">Zht's blog！</router-link>
                    </span>
                </div>
            </div>
            <div id="lv-container" data-id="city" data-uid="MTAyMC80Nzc3Ni8yNDI3Mw==">
                <noscript>为正常使用来必力评论功能请激活JavaScript</noscript>
            </div>
        </template>
        <template v-if="article==undefined" style="text-align: center;">
            <div>请不要乱搜哦！</div>
        </template>
    </div>
</template>

<script>
import { mapActions } from "vuex";
import { addBrowse, addLikearticls, getBrowseAndLike } from "@/http/http.js";
export default {
    metaInfo() {
        return {
            title: this.article.title + " | zht's blog | 曾海涛的博客", // set a title
            meta: [
                {
                    name: "keyWords",
                    content: (() => {
                        let data = [];
                        for (let i = 0; i < this.article.tags.length; i++) {
                            data.push(this.article.tags[i]);
                        }
                        return data;
                    })()
                },
                {
                    name: "author",
                    content: "zht"
                }
            ]
        };
    },
    name: "",
    data() {
        return {
            tagtype: ["success", "info", "warning", "danger"],
            article: {
                title: "",
                tags: [],
                time: "",
                browse: "",
                likearticls: "",
                html: ""
            },
            likeed: false,
            href: ""
        };
    },
    components: {
        MyArticle: () =>
            import(
                "@/views/content/ArticleContent/ArticleContentLeft/MyArticle/MyArticle.vue"
            )
    },
    methods: {
        ...mapActions("article", ["getArticeListById"]),
        getRandomInt(min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        },
        like() {
            if (this.likeed === true) {
                return;
            }
            this.likeed = true;
            this.article.likearticls++;
            addLikearticls(this.article.id);
        }
    },
    created() {
        console.log(window.location.href);
        this.href = window.location.href;
        console.log(this.$route.params);
        let id = parseInt(this.$route.params.id);
        this.getArticeListById(id).then(res => {
            if (res == null) {
                this.article = undefined;
                return;
            }
            //  title:"",
            //     tags:[],
            //     time:"",
            //     browse:"",
            //     likearticls:"",
            //     html:""
            this.article.title = res.title;
            this.article.tags = res.tags;
            this.article.time = res.time;
            this.article.html = res.html;
            this.article.id = res.id;
            console.log(res);
            // this.article = undefined;
        });
        getBrowseAndLike(id).then(res => {
            if (res) {
                this.article.browse = res.browse;
                this.article.likearticls = res.likearticls;
            }
            console.log(res);
        });
    },
    mounted() {
        let id = parseInt(this.$route.params.id);
        addBrowse(id);
        (function(d, s) {
            var j,
                e = d.getElementById("s");

            if (typeof LivereTower === "function") {
                return;
            }

            j = d.createElement(s);
            j.src = "https://cdn-city.livere.com/js/embed.dist.js";
            j.async = true;
            e.parentNode.insertBefore(j, e);
        })(document, "script");
    }
};
</script>

<style scoped lang = "scss">
#lv-container {
    padding: 0px 20px;
}
.ArticleContent {
    height: auto;
    /* background: rgba(43, 55, 66, 0.2); */
    padding: 25px 0px 10px;
    border-radius: 8px;
    /* box-shadow: 0 4px 8px 6px rgba(7, 17, 27, 0.06); */
    box-shadow: 0px 0px 3px rgba(7, 17, 27, 0.2);
    transition: all 0.3s;
    margin-bottom: 20px;
    &:hover {
        /* box-shadow: 0 4px 6px 6px rgba(7, 17, 27, 0.15); */
        box-shadow: 0 0 1px black;
    }
    @media screen and (min-width: 768px) {
        padding-top: 10px;
    }
    @media screen and (min-width: 992px) {
        padding-top: 25px;
    }
    .bottom-like {
        margin: 5px 0;
        text-align: center;
        height: auto;
        span {
            font-size: 25px;
            padding: 10px;
            border: 1px solid black;
            border-radius: 50%;
            cursor: pointer;
            &:hover {
                background: rgba(45, 140, 240, 0.5);
                color: white;
                border: none;
            }
        }
        .likeed {
            background: rgba(45, 140, 240, 0.5);
            color: white;
            border: none;
        }
    }
    .article-tags {
        text-align: center;
        margin: 8px 0;
        .el-tag {
            margin-right: 7px;
        }
    }
    .article-title {
        font-size: 27px;
        line-height: 33px;
        font-weight: 500;
        color: #333;
        margin: 23px 0;
        text-align: center;
    }
    .information-box {
        .leftbox {
            padding: 0 10px;
            .authorAndtime {
                padding: 10px 0;
                text-align: center;
                @media screen and (min-width: 1200px) {
                    text-align: left;
                }
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
        .rightbox {
            padding: 0 10px;
            .readAndlikes {
                padding: 10px 0;
                text-align: center;
                @media screen and (min-width: 1200px) {
                    text-align: right;
                }
                .likes {
                    .likeed {
                        color: #2d8cf0 !important;
                    }
                }
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
                    .hover {
                        color: #2d8cf0;
                        background: 0 0;
                        outline: 0;
                        cursor: pointer;
                        transition: color 0.2s ease;
                    }
                }
            }
        }
    }
    .post-copyright {
        height: auto;
        position: relative;
        box-shadow: 0px 0px 3px rgba(126, 140, 141, 0.5);
        margin: 20px 15px;
        padding: 20px;
        &:hover {
            box-shadow: 0 0 1px black;
        }
        .post-author {
            margin-top: 10px;
            color: #49b1f5;
            a {
                margin-left: 4px;
                text-decoration: underline;
                color: #99a9bf;
                transition: all 0.5s;
                &:hover {
                    color: #49b1f5;
                    text-decoration: none;
                }
            }
        }
        .post-copyright-meta {
            margin-top: 10px;
            color: #49b1f5;
            span {
                color: #000;
                a {
                    text-decoration: underline;
                    color: #99a9bf;
                    &:hover {
                        color: #49b1f5;
                        text-decoration: none;
                    }
                }
            }
        }
        &::after {
            position: absolute;
            top: 0.5rem;
            right: 0.5rem;
            width: 0.8rem;
            height: 0.8rem;
            border-radius: 0.8rem;
            background: #49b1f5;
            content: "";
        }
    }
}
</style>
