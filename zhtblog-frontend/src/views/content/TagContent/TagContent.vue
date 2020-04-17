<template>
    <div class="TagContent">
        <h1 class="tag-title">
            <span class="iconfont icon-biaoqian1"></span>标签
        </h1>
        <div class="tag-list-box">
            <template v-for="(item,index) in taglistData">
                <router-link :to="'/tags/'+item.id" class="tag-list" :key="index">
                    <el-tag :type="tagTypelist[getRandomInt(0,3)]">{{item.name}} ( {{item.num}} )</el-tag>
                </router-link>
            </template>
        </div>
    </div>
</template>

<script>
import { getAllLable } from "@/http/http.js";
import { mapMutations } from "vuex";
export default {
    metaInfo: {
        title: "标签 | zht's blog|曾海涛的博客", // set a title
        meta: [
            {
                name: "author",
                content: "zht"
            }
        ]
    },
    name: "",
    data() {
        return {
            tagTypelist: ["success", "info", "warning", "danger"],
            taglistData: ""
        };
    },
    methods: {
        ...mapMutations("lable", ["setTaglist"]),
        getRandomInt(min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        }
    },
    created() {
        getAllLable().then(res => {
            this.taglistData = res;
            this.setTaglist(res);
            console.log(res);
        });
    }
};
</script>

<style scoped lang="scss">
.TagContent {
    height: auto;
    // box-shadow: 0 4px 8px 6px rgba(7, 17, 27, 0.06);
    box-shadow: 0px 0px 3px rgba(7, 17, 27, 0.2);
    margin: 0 0 20px 0;
    border-radius: 8px;
    padding: 20px 24px 60px 24px;
    min-height: 300px;
    transition: all 0.3s;
    &:hover {
        // box-shadow: 0 4px 12px 12px rgba(7, 17, 27, 0.15);
        box-shadow: 0 0 1px black;
    }
    .tag-list-box {
        margin-top: 20px;
        padding: 0 20px;
        text-align: center;
        .tag-list {
            display: inline-block;
            margin: 5px 5px;
        }
    }

    .tag-title {
        font-size: 30px;
        text-align: center;
        &:hover {
            color: #409eff;
            cursor: pointer;
            transition: all 0.5s ease;
        }
        .iconfont {
            font-size: 100%;
            margin-right: 3px;
        }
    }
}
</style>
