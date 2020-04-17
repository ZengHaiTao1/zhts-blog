<template>
    <div class="EleTag">
        <span class="title">文章标签 :</span>
        <el-tag
            :key="tag"
            v-for="tag in dynamicTags"
            closable
            :type="tagTypelist[getRandomInt(0,3)]"
            :disable-transitions="false"
            @close="handleClose(tag)"
        >{{tag}}</el-tag>
        <el-input
            class="input-new-tag"
            v-if="inputVisible"
            v-model="inputValue"
            ref="saveTagInput"
            size="small"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
        ></el-input>
        <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            // dynamicTags: [],
            inputVisible: false,
            inputValue: "",
            tagTypelist: ["success", "info", "warning", "danger"]
        };
    },
    props:["dynamicTags"],
    methods: {
        handleClose(tag) {
            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
            this.Sendout();
        },

        showInput() {
            this.inputVisible = true;
            this.$nextTick(() => {
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },

        handleInputConfirm() {
            let inputValue = this.inputValue;
            if (inputValue) {
                this.dynamicTags.push(inputValue);
                this.Sendout();
            }
            this.inputVisible = false;
            this.inputValue = "";
        },
        getRandomInt(min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        },
        Sendout() {
            this.$emit("getTag", this.dynamicTags);
        }
    }
};
</script>

<style scoped lang="scss">
.button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}
.input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
}
.EleTag {
    margin: 20px 0;
    .title {
        display: inline-block;
        height: 30px;
        line-height: 30px;
        transition: all 0.2s ease;
    }
    .el-tag {
        margin-left: 10px;
    }
}
</style>
