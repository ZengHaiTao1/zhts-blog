<template>
    <div class="Tags">
        <el-table
            :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%"
            stripe
            height="600"
        >
            <el-table-column label="标签" prop="name"></el-table-column>
            <el-table-column sortable label="数量" prop="num"></el-table-column>
            <el-table-column
                label="显示"
                :filters="[{text: 'true', value: true}, {text: 'false', value: false}]"
                :filter-method="filterTag"
            >
                <template slot-scope="scope">
                    <el-switch v-model="scope.row.display" @change="chang(scope.row)"></el-switch>
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
import {
    getAllTags,
    updateTagDisplay,
    getAllCategoriesPage,
    updateCategoriesDisplay
} from "@/http/http.js";
export default {
    data() {
        return {
            tableData: [
                {
                    num: "",
                    name: "",
                    display: false
                }
            ],
            istag: false,
            iscategory: false,
            search: ""
        };
    },
    methods: {
        chang(par) {
            // console.log(par);
            let tf;
            if (par.display) {
                tf = "1";
            } else {
                tf = "0";
            }
            if (this.istag) {
                updateTagDisplay(par.id, tf);
            } else if (this.iscategory) {
                updateCategoriesDisplay(par.id, tf);
            }
        },
        filterTag(value, row) {
            return row.display === value;
        }
    },
    created() {
        let fullPath = this.$route.fullPath;
        if (fullPath.indexOf("Tags") != -1) {
            this.istag = true;
            getAllTags().then(res => {
                this.tableData = res;
            });
        } else if (fullPath.indexOf("category") != -1) {
            this.iscategory = true;
            getAllCategoriesPage().then(res => {
                this.tableData = res;
            });
        }
    }
};
</script>

<style scoped lang="scss">
.Tags {
    margin-top: 40px;
    padding: 0px 30px;
}
</style>
