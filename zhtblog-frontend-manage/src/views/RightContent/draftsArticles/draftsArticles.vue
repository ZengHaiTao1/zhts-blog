<template>
    <div class="draftsArticles">
        <el-table
            style="width: 100%"
            :data="tableData.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))"
            stripe
            height="600"
        >
            <el-table-column label="ID" prop="id" width="50"></el-table-column>
            <el-table-column label="标题" prop="title"></el-table-column>
            <el-table-column sortable label="创建时间" prop="creattime"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                        icon="el-icon-edit"
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)"
                    >编辑</el-button>
                    <el-button
                        size="mini"
                        type="danger"
                        icon="el-icon-delete"
                        @click="deleteEdit(scope.$index, scope.row)"
                    >删除</el-button>
                </template>
            </el-table-column>
            <el-table-column align="right">
                <template slot="header" slot-scope="scope">
                    <el-input :data-a="scope" v-model="search" size="medium" placeholder="输入关键字搜索" />
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { getAllDraftsarticles, deleteDraftsarticlesById } from "@/http/http.js";
export default {
    name: "",
    data() {
        return {
            tableData: [],
            search: ""
        };
    },
    methods: {
        handleEdit(index, row) {
            this.$router.push({ path: "/admin/setDraftsArticle/" + row.id });
        },
        deleteEdit(index, row) {
            this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
                center: true
            })
                .then(() => {
                    deleteDraftsarticlesById(row.id).then(res => {
                        // console.log(res);
                        for (let i = 0; i < this.tableData.length; i++) {
                            if (this.tableData[i].id === row.id) {
                                this.tableData.splice(i, 1);
                                break;
                            }
                        }
                    });
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消删除"
                    });
                });
        },
        open() {}
    },
    mounted() {},
    created() {
        getAllDraftsarticles().then(res => {
            this.tableData = res;
        });
    }
};
</script>

<style scoped lang="scss">
.draftsArticles {
    margin-top: 40px;
    padding: 0px 30px;
    .el-pagination {
        text-align: center;
        margin-top: 10px;
    }
}
</style>    
