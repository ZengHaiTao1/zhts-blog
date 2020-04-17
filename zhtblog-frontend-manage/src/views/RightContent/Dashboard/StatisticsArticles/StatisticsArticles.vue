<template>
    <div class="StatisticsArticles">
        <v-chart ref="chart" :options="date" autoresize style="width: 100%;height:400px;"></v-chart>
    </div>
</template>

<script>
import { getArticlsByDate } from "@/http/http.js";
export default {
    name: "",
    data() {
        return {
            date: { color: ["#3398DB"] },
            option: {
                color: ["#3398DB"],
                title: {
                    text: "文章数量统计",
                    subtext: "实时统计",
                    x: "center"
                },
                tooltip: {
                    trigger: "axis",
                    axisPointer: {
                        // 坐标轴指示器，坐标轴触发有效
                        type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: "3%",
                    right: "4%",
                    bottom: "3%",
                    containLabel: true
                },
                xAxis: [
                    {
                        type: "category",
                        data: [
                            "一月",
                            "二月",
                            "三月",
                            "四月",
                            "五月",
                            "六月",
                            "七月",
                            "八月",
                            "九月",
                            "十月",
                            "十一月",
                            "十二月"
                        ],
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis: [
                    {
                        type: "value"
                    }
                ],
                series: [
                    {
                        name: "文章数",
                        type: "bar",
                        barWidth: "60%",
                        data: [10, 52, 20, 34, 39, 33, 22, 21, 32, 12, 21, 21]
                    }
                ]
            }
        };
    },
    methods: {},
    mounted() {},
    created() {
        this.$nextTick(() => {
            this.$refs.chart.showLoading();
        });
        getArticlsByDate().then(res => {
            this.$refs.chart.hideLoading();
            this.option.series[0].data = res;
            this.date = this.option;
        });
    }
};
</script>

<style scoped>
</style>
