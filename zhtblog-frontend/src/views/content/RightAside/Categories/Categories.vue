<template>
    <div class="Categories">
        <div class="Categories-title">
            <span class="iconfont icon-shuangsechangyongtubiao-"></span>
            分类
        </div>
        <template v-for="(item,index) in CategoriesList">
            <router-link class="Categories-list" :to="'/categories/'+item.id" :key="index">
                <span class="Categories-name">{{item.categories}}</span>
                <span class="Categories-num">{{item.num}}</span>
            </router-link>
        </template>
        <template v-if="!CategoriesList">
            <a class="Categories-list">
                <span class="Categories-name" style=" background: #f2f2f2;height:20px "></span>
                <span class="Categories-num"></span>
            </a>
        </template>
    </div>
</template>

<script>
import { getCategories } from "@/http/http.js";
export default {
    name: "",
    data() {
        return {
            CategoriesList: undefined
        };
    },
    methods: {},
    created() {
        getCategories().then(res => {
            this.CategoriesList = res;
        });
    }
};
</script>

<style scoped lang="scss">
.Categories {
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
    .Categories-title {
        text-align: center;
        font-size: 18px;
    }
    .Categories-list {
        color: #000;
        border-top: 1px solid #eee;
        display: block;
        margin-top: 10px;
        padding: 10px 0;
        font-size: 14px;
        &:hover {
            background: #49b1f5;
            .Categories-name {
                left: 6px;
            }
            .Categories-num {
                left: 4px;
            }
        }
        .Categories-name {
            display: inline-block;
            width: 80%;
            position: relative;
            left: 10px;
            transition: all 0.3s ease;
        }
        .Categories-num {
            position: relative;
            width: 20%;
            transition: all 0.3s ease;
        }
    }
}
</style>
