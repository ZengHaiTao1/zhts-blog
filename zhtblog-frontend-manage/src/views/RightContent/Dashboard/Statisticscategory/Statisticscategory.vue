<template>
    <div class="Statisticscategory">
        <v-chart ref="chart" :options="data" autoresize style="width: 100%;height:400px;"></v-chart>
    </div>
</template>

<script>
import { getAllCategories } from "@/http/http.js";
export default {
    name: "",
    data() {
        return {
            data: {
                title: {
                    text: "文章分类情况",
                    subtext: "实时数据",
                    x: "center"
                }
            },
            option: {
                title: {
                    text: "文章分类情况",
                    subtext: "实时数据",
                    x: "center"
                },
                tooltip: {
                    trigger: "item",
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: "vertical",
                    left: "left",
                    // data: ["webpack", "vue", "js", "css3", "typescript", "es6"]
                    data: []
                },
                series: [
                    {
                        name: "文章数量",
                        type: "pie",
                        radius: "55%",
                        center: ["50%", "60%"],
                        // data: [
                        //     { value: 335, name: "webpack" },
                        // ]
                        data: []
                    }
                ]
            }
        };
    },
    methods: {},
    created() {
        this.$nextTick(() => {
            this.$refs.chart.showLoading();
        });
        getAllCategories().then(res => {
            this.$refs.chart.hideLoading();
            this.option.series[0].data = res;
            let nameArr = [];
            for (let i = 0; i < res.length; i++) {
                nameArr.push(res[i].name);
            }
            this.option.legend.data = nameArr;
            this.data = this.option;
        });
    }
};
</script>

<style scoped lang="scss">
.Statisticscategory {
    margin-top: 20px;
}
</style>
