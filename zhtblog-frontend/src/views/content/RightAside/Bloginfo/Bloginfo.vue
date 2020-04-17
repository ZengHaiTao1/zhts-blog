<template>
    <div class="Bloginfo">
        <div class="Bloginfo-title">
            <span class="iconfont icon-wangzhankaifa"></span>
            网站资讯
        </div>
        <template v-for="(item,index) in BloginfoList">
            <a class="Bloginfo-list" :key="index">
                <span class="Bloginfo-name">{{item.name}}</span>
                <span class="Bloginfo-num">{{item.num}}</span>
            </a>
        </template>
    </div>
</template>

<script>
export default {
    name: "",
    data() {
        return {
            BloginfoList: [
                {
                    name: "文章数目",
                    num: "25"
                },
                {
                    name: "已运行时间",
                    num: "15天"
                },
                {
                    name: "本站访客数",
                    num: "288"
                },
                {
                    name: "本站总访问量",
                    num: "1243"
                }
            ]
        };
    },
    methods: {}
};
</script>

<style scoped lang="scss">
.Bloginfo {
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
    .Bloginfo-title {
        text-align: center;
        font-size: 18px;
    }
    .Bloginfo-list {
        color: #000;
        border-top: 1px solid #eee;
        display: block;
        margin-top: 10px;
        padding: 10px 0;
        font-size: 14px;
        .Bloginfo-name {
            display: inline-block;
            width: 70%;
            position: relative;
            left: 10px;
        }
        .Bloginfo-num {
            position: relative;
            width: 20%;
        }
    }
}
</style>
