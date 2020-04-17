<template>
    <div class="Top">
        <span class="iconfont icon-caidan" @click="chang"></span>
        <img :src="$publicPath+'img/logo.png'" alt="logo" />
    </div>
</template>

<script>
export default {
    name: "",
    data() {
        return {};
    },
    methods: {
        chang() {
            this.bus.$emit("update", ""); // data就是触发update事件要带走的数据
        }
    }
};
</script>

<style scoped lang="scss">
.Top {
    height: 30px;
    padding: 10px;
    border-bottom: 1px solid black;
    text-align: center;
    position: relative;
    .iconfont {
        display: inline-block;
        font-size: 20px;
        height: 100%;
        vertical-align: middle;
        cursor: pointer;
        position: absolute;
        left: 10px;
        top: 15px;
    }
    img {
        display: inline-block;
        height: 30px;
    }
}
</style>
