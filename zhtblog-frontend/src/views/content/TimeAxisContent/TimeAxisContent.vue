<template>
    <div class="TimeAxisContent">
        <div class="timeline-header">
            <span class="square"></span>
            <span class="main-title">{{thisname}}</span>
            <span class="vertical-line"></span>
            <span class="sub-title">{{thisTitle}}</span>
        </div>
        <!-- <div class="mainContent">
            <div class="year">
                <span class="line"></span>
                <span class="iconfont icon-changyongtubiao-xianxingdaochu-zhuanqu- year-icon"></span>
                <span class="year-title">2019</span>
                <span class="vertical-line"></span>
                <span class="article-list-num">
                    共
                    <a>61</a>
                    篇
                </span>
            </div>
            <div class="month-box">
                <div class="month">
                    <span class="line"></span>
                    <span class="iconfont icon-shijian month-icon"></span>
                    <span class="month-title">11月</span>
                    <span class="vertical-line"></span>
                    <span class="month-list-num">
                        共
                        <a>4</a> 篇
                    </span>
                </div>
                <div class="archive-list-cell">
                    <div class="info">
                        <el-tag type="success">2019-11-28</el-tag>
                        <a href="#" class="title-name">阿里巴巴面经记</a>
                    </div>
                    <div class="info">
                        <el-tag type="success">2019-11-28</el-tag>
                        <a href="#" class="title-name">阿里巴巴面经记</a>
                    </div>
                    <div class="info">
                        <el-tag type="success">2019-11-28</el-tag>
                        <a href="#" class="title-name">阿里巴巴面经记</a>
                    </div>
                </div>
            </div>
        </div>-->
        <template v-for="(item , index ) in articleData">
            <div class="mainContent" :key="index">
                <div class="year">
                    <span class="line"></span>
                    <span class="iconfont icon-changyongtubiao-xianxingdaochu-zhuanqu- year-icon"></span>
                    <span class="year-title">{{item.year}}</span>
                    <span class="vertical-line"></span>
                    <span class="article-list-num">
                        共
                        <a>{{item.num}}</a>
                        篇
                    </span>
                </div>
                <template v-for="(itemSecond,indexSecond) in item.month">
                    <div class="month-box" :key="indexSecond" v-if="itemSecond.num!=0">
                        <div class="month">
                            <span class="line"></span>
                            <span class="iconfont icon-shijian month-icon"></span>
                            <span class="month-title">{{itemSecond.monthTitle}}月</span>
                            <span class="vertical-line"></span>
                            <span class="month-list-num">
                                共
                                <a>{{itemSecond.num}}</a> 篇
                            </span>
                        </div>
                        <div class="archive-list-cell">
                            <template v-for="(itemThird,indexThird) in itemSecond.articlelist">
                                <div class="info" :key="indexThird">
                                    <el-tag>{{itemThird.time}}</el-tag>
                                    <router-link
                                        :to="'/post/'+itemThird.id"
                                        class="title-name"
                                    >{{itemThird.title}}</router-link>
                                </div>
                            </template>
                        </div>
                    </div>
                </template>
            </div>
        </template>
    </div>
</template>

<script>
import {
    getTimeAxis,
    getTimeAxisByTag,
    getTimeAxisByCategories
} from "@/http/http.js";
import { mapActions } from "vuex";
export default {
    metaInfo() {
        return {
            title:
                this.thisname +
                " | " +
                this.thisTitle +
                " | zht's blog | 曾海涛的博客", // set a title
            meta: [
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
            thisname: "存档时光",
            thisTitle: "Articles",
            articleData: ""
        };
    },
    methods: {
        ...mapActions("lable", ["getTagNameById"]),
        ...mapActions("categories", ["getCategoriesNameById"])
    },
    created() {
        console.log(this.$route);
        let path = this.$route.fullPath;
        let id = parseInt(this.$route.params.id);
        if (path.indexOf("/tags/") != -1) {
            this.thisname = "标签";
            this.thisTitle = "";
            // console.log(this.getTagNameById);
            this.getTagNameById(id).then(res => {
                console.log(res);
                this.thisTitle = res.name;
            });
            getTimeAxisByTag(id).then(res => {
                console.log(res);
                this.articleData = res;
            });
            return;
        } else if (path.indexOf("/categories/") != -1) {
            this.thisname = "分类";
            this.thisTitle = "";
            console.log(id);
            this.getCategoriesNameById(id).then(res => {
                console.log(res);
                this.thisTitle = res.categories;
            });
            getTimeAxisByCategories(id).then(res => {
                console.log(res);
                this.articleData = res;
            });
            return;
        }
        getTimeAxis().then(res => {
            this.articleData = res;
            console.log(res);
        });
    }
};
</script>

<style scoped lang = "scss">
.TimeAxisContent {
    height: auto;
    /* box-shadow: 0 4px 8px 6px rgba(7, 17, 27, 0.06); */
    box-shadow: 0px 0px 3px rgba(7, 17, 27, 0.2);
    border-radius: 8px;
    padding: 10px 0 40px 20px;
    transition: all 0.3s;
    min-height: 300px;
    margin-bottom: 20px;
    @media only screen and (max-width: 768px) {
        padding: 10px 3px;
    }
    &:hover {
        /* box-shadow: 0 4px 12px 12px rgba(7, 17, 27, 0.15); */
        box-shadow: 0 0 1px black;
    }
    .timeline-header {
        padding: 20px 0 0 7px;
        height: 90px;
        line-height: 50px;
        text-align: left;
        box-sizing: border-box;
        .square {
            display: inline-block;
            height: 30px;
            width: 30px;
            background: #000;
            vertical-align: middle;
        }
        .main-title {
            display: inline-block;
            vertical-align: middle;
            font-size: 22px;
            padding: 0 10px;
        }
        .vertical-line {
            display: inline-block;
            height: 20px;
            width: 1px;
            background-color: #333;
            vertical-align: middle;
        }
        .sub-title {
            display: inline-block;
            vertical-align: middle;
            font-size: 18px;
            padding-left: 10px;
        }
    }
    .mainContent {
        .year {
            padding: 15px 0;
            line-height: 50px;
            position: relative;
            text-align: left;
            .line {
                left: 0;
                top: 0;
                bottom: 0;
                position: absolute;
                padding: 15px 0;
                &::before {
                    content: "";
                    position: absolute;
                    top: 0;
                    left: 17px;
                    width: 4px;
                    background-color: #409eff;
                    height: 20%;
                    border-radius: 0 0 2px 2px;
                }
                &::after {
                    content: "";
                    position: absolute;
                    bottom: 0;
                    left: 17px;
                    width: 4px;
                    background-color: #409eff;
                    height: 20%;
                    border-radius: 2px 2px 0 0;
                }
            }
            .year-icon {
                display: inline-block;
                font-size: 40px;
                color: #53a8ff;
                vertical-align: middle;
            }
            .year-title {
                display: inline-block;
                vertical-align: middle;
                font-size: 22px;
                padding: 0 10px;
            }
            .vertical-line {
                display: inline-block;
                height: 20px;
                width: 1px;
                background-color: #333;
                vertical-align: middle;
            }
            .article-list-num {
                display: inline-block;
                vertical-align: middle;
                font-size: 18px;
                padding-left: 10px;
                a {
                    color: #7e8c8d;
                    &:hover {
                        outline: 0;
                        text-decoration: none;
                        color: #57a3f3;
                        cursor: pointer;
                    }
                }
            }
        }
        .month {
            position: relative;
            text-align: left;
            padding: 15px 0;
            line-height: 30px;
            .line {
                left: 0;
                top: 0;
                bottom: 0;
                position: absolute;
                padding: 15px 0;
                &::before {
                    content: "";
                    position: absolute;
                    top: 0;
                    left: 17px;
                    width: 4px;
                    background-color: #409eff;
                    height: 20%;
                    border-radius: 0 0 2px 2px;
                }
                &::after {
                    content: "";
                    position: absolute;
                    bottom: 0;
                    left: 17px;
                    width: 4px;
                    background-color: #409eff;
                    height: 20%;
                    border-radius: 2px 2px 0 0;
                }
            }
            .month-icon {
                display: inline-block;
                margin-left: 8px;
                font-size: 24px;
                color: #5c5c5c;
                vertical-align: middle;
            }
            .month-title {
                display: inline-block;
                vertical-align: middle;
                font-size: 18px;
                padding: 0 10px;
            }
            .vertical-line {
                display: inline-block;
                height: 20px;
                width: 1px;
                background-color: #333;
                vertical-align: middle;
            }
            .month-list-num {
                display: inline-block;
                vertical-align: middle;
                font-size: 14px;
                padding-left: 10px;
                a {
                    color: #7e8c8d;
                    &:hover {
                        outline: 0;
                        color: #57a3f3;
                        cursor: pointer;
                    }
                }
            }
        }
        .archive-list-cell {
            position: relative;
            padding: 5px 0;
            .info {
                font-size: 17px;
                line-height: 25px;
                margin-left: 45px;
                padding: 5px 0;
                &::after {
                    content: "";
                    position: absolute;
                    min-height: 25px;
                    top: 0;
                    bottom: 0;
                    left: 17px;
                    width: 4px;
                    background: #409eff;
                }
                .title-name {
                    margin-left: 10px;
                    color: #000;
                    word-break: break-all;
                    &:hover {
                        color: #57a3f3;
                    }
                }
            }
        }
    }
}
</style>
