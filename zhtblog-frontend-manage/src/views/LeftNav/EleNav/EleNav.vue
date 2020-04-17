<template>
    <div class="EleNav" :class="isCollapse?'open':''">
        <!-- <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
            <el-radio-button :label="false">展开</el-radio-button>
            <el-radio-button :label="true">收起</el-radio-button>
        </el-radio-group>-->
        <el-menu
            default-active="1-4-1"
            class="el-menu-vertical-demo"
            :collapse="isCollapse"
            background-color="rgb(48, 65, 86)"
            text-color="rgb(191, 203, 217)"
            router
        >
            <el-menu-item index="/admin">
                <!-- <i class="el-icon-menu"></i> -->
                <span class="iconfont icon-yibiaopan" style="margin-right:5px"></span>
                <span slot="title">仪表盘</span>
            </el-menu-item>
            <el-submenu index>
                <template slot="title">
                    <span class="iconfont icon-wenzhang" style="margin-right:5px"></span>
                    <span slot="title">文章</span>
                </template>
                <el-menu-item index="/admin/Allarticles/1">
                    <span class="iconfont icon-quanbu" style="margin-right:5px"></span>
                    所有文章
                </el-menu-item>
                <el-menu-item index="/admin/Editor">
                    <span class="iconfont icon-xie" style="margin-right:5px"></span>
                    写文章
                </el-menu-item>
                <el-menu-item index="/admin/draftsArticles">
                    <span class="iconfont icon-caogaoxiang" style="margin-right:5px"></span>
                    草稿箱
                </el-menu-item>
            </el-submenu>
            <el-menu-item index="/admin/Notice">
                <span class="iconfont icon-gonggao" style="margin-right:5px"></span>
                <span slot="title">公告</span>
            </el-menu-item>
            <el-menu-item index="/admin/Tags">
                <span class="iconfont icon-biaoqian" style="margin-right:5px"></span>
                <span slot="title">标签</span>
            </el-menu-item>
            <el-menu-item index="/admin/category">
                <span class="iconfont icon-ziyuan9" style="margin-right:5px"></span>
                <span slot="title">分类</span>
            </el-menu-item>
            <el-menu-item @click="loginOut">
                <span class="iconfont icon-tuichu" style="margin-right:5px"></span>
                <span slot="title">退出</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
import { loginOut } from "@/http/http";
export default {
    data() {
        return {
            isCollapse: false
        };
    },
    methods: {
        loginOut() {
            this.$confirm("是否退出登陆？", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
                center: true
            })
                .then(() => {
                    loginOut().then(res => {
                        console.log(res);
                        window.localStorage.removeItem("zhtblog_token");
                        this.$router.push({ path: "/login" });
                    });
                })
                .catch(() => {});
        }
        // handleOpen(key, keyPath) {
        //     console.log(key, keyPath);
        // },
        // handleClose(key, keyPath) {
        //     console.log(key, keyPath);
        // }
    },
    mounted() {
        this.bus.$on("update", () => {
            this.isCollapse = !this.isCollapse;
        });
    }
};
</script>

<style  lang="scss" scope>
.el-menu {
    border-right: solid 1px transparent;
    width: 100%;
    .el-menu-item {
        .el-tooltip {
            display: inline-block;
        }
        &:hover {
            background-color: #001528 !important;
        }
    }
    .el-submenu {
        .el-submenu__title {
            &:hover {
                background-color: #001528 !important;
            }
        }
    }
}
.EleNav {
    transition: width 0.28s ease-in-out;
    width: 210px !important;
    background-color: #304156;
    height: 100%;
    position: fixed;
    font-size: 0;
    top: 0;
    bottom: 0;
    left: 0;
    z-index: 1001;
    overflow: hidden;
}
.open {
    width: 70px !important;
}
.el-menu--collapse > .el-menu-item span,
.el-menu--collapse > .el-submenu > .el-submenu__title span.iconfont {
    visibility: visible !important;
    width: auto !important;
    height: auto !important;
}
</style>
