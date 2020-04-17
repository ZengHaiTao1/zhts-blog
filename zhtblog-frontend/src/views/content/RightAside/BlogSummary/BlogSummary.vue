<template>
    <div :class="wowclass" data-wow-duration="2s" data-wow-delay="0.5s" class="BlogSummary">
        <template v-if="summary">
            <div class="img-wrapper">
                <img v-lazy="'http://cdn.zhtblog.cn/avatar.png'" />
            </div>
            <p class="BlogSummary-title">zht</p>
            <p class="BlogSummary-description">{{summary.description}}</p>
            <table>
                <tr>
                    <th>文章</th>
                    <th>标签</th>
                    <th>分类</th>
                </tr>
                <tr>
                    <td>
                        <router-link to="/archives">{{summary.postnum}}</router-link>
                    </td>
                    <td>
                        <router-link to="/tags">{{summary.tagnum}}</router-link>
                    </td>
                    <td>
                        <router-link to="/categories">{{summary.categoriesnum}}</router-link>
                    </td>
                </tr>
            </table>
        </template>
        <template v-if="!summary">
            <div class="img-wrapper">
                <img style=" background: #f2f2f2;height: 120px;border:0" />
            </div>
            <p class="BlogSummary-title">zht</p>
            <p class="BlogSummary-description"></p>
            <table>
                <tr>
                    <th>文章</th>
                    <th>标签</th>
                    <th>分类</th>
                </tr>
                <tr>
                    <td>
                        <a style=" background: #f2f2f2; "></a>
                    </td>
                    <td>
                        <a style=" background: #f2f2f2; "></a>
                    </td>
                    <td>
                        <a style=" background: #f2f2f2; "></a>
                    </td>
                </tr>
            </table>
        </template>
    </div>
</template>

<script>
import { getBlogSummary } from "@/http/http.js";
export default {
    name: "",
    data() {
        return {
            wowclass: "wow fadeIn",
            summary: undefined
        };
    },
    methods: {},
    mounted() {
        setTimeout(() => {
            this.wowclass = "";
        }, 4000);
    },
    created() {
        getBlogSummary().then(res => {
            this.summary = res;
        });
    }
};
</script>

<style scoped lang ="scss">
.BlogSummary {
    padding: 10px 12px;
    /* background: rgb(115, 45, 22); */
    height: auto;
    box-shadow: 0px 0px 3px rgba(7, 17, 27, 0.2);
    border-radius: 8px;
    transition: all 0.3s;
    margin-bottom: 20px;
    &:hover {
        /* box-shadow: 0 4px 12px 12px rgba(7, 17, 27, 0.15); */
        box-shadow: 0 0 1px black;
    }
    .img-wrapper {
        width: 120px;
        /* background: red; */
        margin: 10px auto;
        img {
            display: inline-block;
            width: 100%;
            border-radius: 50%;
            transition: all 0.3s;
            &:hover {
                transform: rotate(540deg);
                box-shadow: 0 0 8px 0 rgba(232, 237, 250, 0.6),
                    0 2px 4px 0 rgba(232, 237, 250, 0.5);
            }
        }
    }
    .BlogSummary-title {
        text-align: center;
    }
    .BlogSummary-description {
        text-align: center;
        font-size: 14px;
        margin: 15px 0 15px 0;
        padding: 0 5px 0 5px;
    }
    table {
        width: 100%;
        text-align: center;
        tr {
            th {
                height: 40px;
            }
            td {
                a {
                    /* padding: 20px 0; */
                    border: 10px solid transparent;
                    color: #000;
                }
                height: 40px;
            }
        }
    }
}
</style>
