<template>
    <div class="Accesslog">
        <h1 class="title">访问日志</h1>
        <el-table
            stripe
            height="600"
            :data="tableData.filter(data => !search || data.place.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%"
        >
            <el-table-column prop="intotime" label="进入时间" width="180"></el-table-column>
            <el-table-column prop="ip" label="IP" width="180"></el-table-column>
            <el-table-column prop="place" label="所在地"></el-table-column>
            <el-table-column prop="leavetime" label="离开时间" width="180"></el-table-column>
            <el-table-column align="right">
                <template slot="header" slot-scope="scope">
                    <el-input :data-a="scope" v-model="search" size="medium" placeholder="根据所在地搜索" />
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            @current-change="changPage"
            :page-size="10"
            :total="visitorNum"
            background
            layout="prev, pager, next"
        ></el-pagination>
    </div>
</template>

<script>
import { getVisitorByPage, getVisitorNum } from "@/http/http.js";
export default {
    name: "",
    data() {
        return {
            search: "",
            tableData: [],
            visitorNum: 10
        };
    },
    methods: {
        changPage(val) {
            // console.log(`当前页: ${val}`);
            getVisitorByPage(val).then(res => {
                // console.log(res);
                this.tableData = res;
            });
        }
    },
    mounted() {},
    created() {
        getVisitorByPage(1).then(res => {
            this.tableData = res;
        });
        getVisitorNum().then(res => {
            console.log(res);
            this.visitorNum = res;
        });
    }
};
</script>

<style scoped lang="scss">
.Accesslog {
    margin-top: 20px;
    .title {
        text-align: center;
        font-size: 22px;
        font-weight: 600;
    }
    .el-pagination {
        text-align: center;
        margin-top: 10px;
    }
}
</style>
