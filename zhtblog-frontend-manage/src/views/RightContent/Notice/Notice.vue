<template>
    <div class="Notice">
        <div class="box vertical-center">
            <el-input placeholder="请输入内容" v-model="Notice">
                <template slot="prepend">公告</template>
            </el-input>
            <el-button type="primary" @click="Submission">修改</el-button>
        </div>
    </div>
</template>

<script>
import { getNotice, updateNotice } from "@/http/http.js";
export default {
    name: "",
    data() {
        return {
            Notice: ""
        };
    },
    methods: {
        Submission() {
            let Notice = this.Notice;
            if (Notice === "") {
                this.open("公告不能为空");
            }
            updateNotice(Notice);
        },
        open(text) {
            this.$message({
                message: text,
                type: "warning",
                offset: 200,
                center: true,
                showClose: true,
                duration: 3000,
                onClose: () => {
                    this.openbutton();
                }
            });
        }
    },
    created() {
        getNotice().then(res => {
            this.Notice = res.notice;
        });
    }
};
</script>

<style scoped lang="scss">
.Notice {
    position: relative;
    width: 100%;
    height: 100%;
    .box {
        width: 100%;
        .el-input {
            width: 800px;
            margin: 0 auto;
            margin-left: 20px;
        }
        .el-button {
            margin-left: 10px;
        }
    }
}
</style>
