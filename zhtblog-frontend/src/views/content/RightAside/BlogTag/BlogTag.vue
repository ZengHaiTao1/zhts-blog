<template>
    <div class="BlogTag">
        <div class="BlogTag-title">
            <span class="iconfont icon-biaoqian1"></span>
            标签
        </div>
        <template v-for="(item ,index) in taglist ">
            <router-link :to="'/tags/'+item.id" class="tag-list" :key="index">
                <el-tag :type="tagTypelist[getRandomInt(0,3)]">{{item.name}}</el-tag>
            </router-link>
        </template>
        <template v-if="!taglist">
            <a class="tag-list">
                <el-tag :type="tagTypelist[getRandomInt(0,3)]"></el-tag>
            </a>
            <a class="tag-list">
                <el-tag :type="tagTypelist[getRandomInt(0,3)]"></el-tag>
            </a>
            <a class="tag-list">
                <el-tag :type="tagTypelist[getRandomInt(0,3)]"></el-tag>
            </a>
        </template>
    </div>
</template>

<script>
import { getLableByDisplay } from "@/http/http.js";
export default {
    name: "",
    data() {
        return {
            tagTypelist: ["success", "info", "warning", "danger"],
            taglist: ""
        };
    },
    methods: {
        getRandomInt(min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        }
    },
    created() {
        getLableByDisplay().then(res => {
            this.taglist = res;
        });
    }
};
</script>

<style scoped lang="scss">
.BlogTag {
    height: auto;
    margin-bottom: 20px;
    // box-shadow: 0 4px 8px 6px rgba(7, 17, 27, 0.06);
    box-shadow: 0px 0px 3px rgba(7, 17, 27, 0.2);
    border-radius: 8px;
    padding: 20px 24px;
    transition: all 0.3s;
    &:hover {
        // box-shadow: 0 4px 12px 12px rgba(7, 17, 27, 0.15);
        box-shadow: 0 0 1px black;
    }
    .BlogTag-title {
        text-align: center;
        font-size: 18px;
        margin-bottom: 10px;
    }
    // .tag-list {
    //     padding: 10px 0 0 0;
    //     .el-tag {
    //         margin-left: 5px;
    //         margin-top: 5px;
    //         &:hover {
    //             opacity: 0.7;
    //         }
    //     }
    // }
    .tag-list {
        display: inline-block;
        margin: 5px 5px 3px 10px;
    }
}
</style>
