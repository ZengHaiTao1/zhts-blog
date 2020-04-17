<template>
    <div class="categoriesContent">
        <h1 class="categories-title">
            <span class="iconfont icon-fenlei"></span>分类
        </h1>
        <div class="categories-list-box">
            <template v-for="(item,index) in categoriesListData">
                <router-link class="categories-list" :to="'/categories/'+item.id" :key="index">
                    <span class="iconfont icon-tongxinyuantu"></span>
                    {{item.categories}}
                    <span class="num">({{item.num}})</span>
                </router-link>
            </template>
        </div>
    </div>
</template>

<script>
import { getAllCategories } from "@/http/http.js";
import { mapMutations } from "vuex";
export default {
    metaInfo: {
        title: "分类 | zht's blog|曾海涛的博客", // set a title
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
            categoriesListData: ""
        };
    },
    methods: {
        ...mapMutations("categories", ["setCategorielist"])
    },
    created() {
        getAllCategories().then(res => {
            this.categoriesListData = res;
            this.setCategorielist(res);
        });
    }
};
</script>

<style scoped lang="scss">
.categoriesContent {
    height: auto;
    min-height: 300px;
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
    .categories-title {
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
    .categories-list-box {
        margin-top: 20px;
        .categories-list {
            display: block;
            color: #000;
            font-size: 17px;
            margin-top: 5px;
            &:hover {
                color: #409eff;
            }
            .icon-tongxinyuantu {
                font-size: 100%;
                margin-right: 3px;
            }
            .num {
                color: #99a9bf;
                margin-left: 3px;
            }
        }
    }
}
</style>
