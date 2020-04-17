<template>
    <div class="Allarticles" v-if="tableData">
        <el-table
            style="width: 100%"
            :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
            stripe
            height="600"
        >
            <el-table-column label="ID" prop="id" width="50"></el-table-column>
            <el-table-column label="标题" prop="title" width="330"></el-table-column>
            <el-table-column sortable label="创建时间" prop="time"></el-table-column>
            <el-table-column sortable label="点赞数" prop="likearticls" width="100"></el-table-column>
            <el-table-column sortable label="阅读量" prop="browse" width="100"></el-table-column>
            <el-table-column
                label="类别"
                prop="category"
                :filters="filters"
                :filter-method="filterHandler"
            ></el-table-column>
            <el-table-column
                label="推荐阅读"
                :filters="[{text: 'true', value: true}, {text: 'false', value: false}]"
                :filter-method="filterRecommend"
            >
                <template slot-scope="scope">
                    <el-switch v-model="scope.row.recommended" @change="chang(scope.row)"></el-switch>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                        icon="el-icon-edit"
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)"
                    >编辑</el-button>
                </template>
            </el-table-column>
            <el-table-column align="right">
                <template slot="header" slot-scope="scope">
                    <el-input :data-a="scope" v-model="search" size="medium" placeholder="输入关键字搜索" />
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            @current-change="changPage"
            :page-size="10"
            :total="articlNum"
            :current-page="parseInt(this.$route.params.id)"
            background
            layout="prev, pager, next"
        ></el-pagination>
    </div>
</template>

<script>
import { getArticlNum, setArticlDisplay } from "@/http/http.js";
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
    data() {
        return {
            tableData: "",
            search: "",
            articlNum: 10,
            filters: ""
        };
    },
    mounted() {},
    methods: {
        ...mapMutations(["setArticlNum"]),
        ...mapActions(["getArticlsByPage"]),
        handleEdit(index, row) {
            // console.log(index, row);
            this.$router.push({ path: "/admin/setArticle/" + row.id });
        },
        chang(par) {
            // console.log(par);
            let tf;
            if (par.recommended) {
                tf = "1";
            } else {
                tf = "0";
            }
            setArticlDisplay(par.id, tf);
        },
        filterHandler(value, row, column) {
            const property = column["property"];
            return row[property] === value;
        },
        filterRecommend(value, row) {
            return row.recommended === value;
        },
        changPage(val) {
            this.$router.push({ path: "/admin/Allarticles/" + val });
        },
        distinct(a) {
            let arr = a;
            let result = [];
            let obj = {};
            for (let i of arr) {
                if (!obj[i]) {
                    result.push(i);
                    obj[i] = 1;
                }
            }
            return result;
        }
    },
    created() {
        let id = this.$route.params.id;
        this.getArticlsByPage(id).then(res => {
            this.tableData = res;
            let arr = [];
            for (let i = 0; i < this.tableData.length; i++) {
                arr.push(this.tableData[i].category);
            }
            arr = this.distinct(arr);
            let newArr = [];
            for (let i = 0; i < arr.length; i++) {
                newArr.push({
                    text: arr[i],
                    value: arr[i]
                });
            }
            this.filters = newArr;
        });
        if (this.getArticlNum != "") {
            this.articlNum = this.getArticlNum;
        } else {
            getArticlNum().then(res => {
                this.articlNum = res;
                this.setArticlNum(res);
            });
        }
    },
    computed: {
        ...mapGetters(["getArticlNum"])
    }
};
</script>

<style scoped lang="scss">
.Allarticles {
    margin-top: 40px;
    padding: 0px 30px;
    margin-bottom: 40px;
    .el-pagination {
        text-align: center;
        margin-top: 10px;
    }
}
</style>
