<template>
    <div :class="wowclass" data-wow-duration="2s" data-wow-delay="1.5s" class="RecommendedReading">
        <h4 class="RecommendedReading-title">
            <span class="iconfont icon-yuedu"></span>
            推荐阅读
        </h4>
        <template v-for="(item,index) in ReadingData">
            <router-link class="Blog-title" :to="'/post/'+item.id" :key="index">
                <p class="title">{{item.title}}</p>
                <p class="info">
                    <span class="time">At time/ {{item.time}}</span>
                </p>
                <p class="read">{{item.browse}} 阅读</p>
                <p class="like">{{item.likearticls}} 喜欢</p>
            </router-link>
        </template>
        <template v-if="!ReadingData">
            <a class="Blog-title">
                <p class="title" style=" background: #f2f2f2;height:30px "></p>
                <p class="info">
                    <span class="time">At time/</span>
                </p>
                <p class="read">阅读</p>
                <p class="like">喜欢</p>
            </a>
        </template>
    </div>
</template>

<script>
import { getRecommendedReading } from "@/http/http.js";
export default {
    name: "",
    data() {
        return {
            wowclass: "wow fadeIn",
            ReadingData: []
        };
    },
    mounted() {
        setTimeout(() => {
            this.wowclass = "";
        }, 4500);
    },
    methods: {},
    created() {
        getRecommendedReading().then(res => {
            this.ReadingData = res;
        });
    },
    watch: {
        ReadingData() {
            this.$nextTick(function() {
                this.bus.$emit("height", "");
            });
        }
    }
};
</script>

<style scoped lang="scss">
.RecommendedReading {
    height: auto;
    margin-bottom: 20px;
    // margin-top: 30px;
    // box-shadow: 0 4px 8px 6px rgba(7, 17, 27, 0.06);
    box-shadow: 0px 0px 3px rgba(7, 17, 27, 0.2);
    border-radius: 8px;
    padding: 20px 24px;
    transition: all 0.3s;
    &:hover {
        // box-shadow: 0 4px 12px 12px rgba(7, 17, 27, 0.15);
        box-shadow: 0 0 1px black;
    }
    .RecommendedReading-title {
        text-align: center;
        font-size: 18px;
        // background: #eff7fb;
    }
    .Blog-title {
        border-top: 1px solid #eee;
        display: block;
        margin-top: 10px;
        padding: 10px 0;
        color: #000;
        word-break: break-all;
        .title {
            word-break: break-all;
        }
        &:hover {
            .title {
                color: #2d8cf0;
            }
        }
        .info {
            margin: 5px 0 0;
            padding: 5px 0;
            span {
                font-size: 13px;
                line-height: 18px;
                font-weight: 100;
                color: #878d99;
            }
        }
        .read {
            padding: 5px 0;
            font-size: 13px;
            line-height: 18px;
            font-weight: 100;
            color: #878d99;
        }
        .like {
            padding: 5px 0;
            font-size: 13px;
            line-height: 18px;
            font-weight: 100;
            color: #878d99;
        }
    }
}
</style>
