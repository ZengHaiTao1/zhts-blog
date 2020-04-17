<template>
    <div class="myArticle" v-html="html"></div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
    name: "",
    data() {
        return {};
    },
    props: ["html"],
    methods: {
        ...mapMutations("article", [
            "addBrowse" // 将 `this.increment()` 映射为 `this.$store.commit('increment')`
        ])
    },
    watch: {
        html() {
            this.$nextTick(function() {
                this.bus.$emit("update", "");
            });
        }
    },
    created() {
        // this.bus.$emit("update", "");
        this.$nextTick(function() {
            this.bus.$emit("update", "");
        });
    }
};
</script>

<style  lang="scss">
.myArticle {
    box-sizing: border-box;
    height: auto;
    // box-shadow: 0px 0px 3px rgba(126, 140, 141, 0.5);
    // margin: 20px 15px;
    padding: 20px 0;
    word-break: break-all;
    @media screen and (max-width: 768px) {
        margin: 10px 0;
        // box-shadow: 0px 0px 3px transparent;
    }
    ul {
        padding-left: 20px;
        li:before {
            display: inline-block;
            content: "●"; //'\25CF'
            padding-right: 10px;
            color: #666;
            font-size: 6px;
        }
    }
    img {
        max-width: 80%;
        width: auto;
        // position: relative;
        // left: 50%;
        // transform: translateX(-50%);
        padding: 0 20px;
        display: block;
        margin: 10px 0;
        @media screen and (max-width: 768px) {
            padding: 0 10px;
            margin: 5px 0;
        }
    }
    // &:hover {
    //     // box-shadow: 0 0 1px black;
    // }
    h1 {
        font-size: 24px;
        margin: 15px 0;
        text-align: center;
        color: #333;
        font-weight: 700;
        padding: 5px 0;
        background: #f5f5f5;
        border-left: 5px solid #409eff;
        border-right: 5px solid #409eff;
    }
    h2 {
        font-size: 20px;
        margin: 15px 0;
        text-align: center;
        border-left: 5px solid #9dd4ff;
        color: #333;
        font-weight: 600;
        padding: 5px 0;
        border-bottom: 1px solid #9dd4ff;
    }
    h3,
    h4,
    h5,
    h6 {
        padding: 0 20px;
        @media screen and (max-width: 768px) {
            padding: 0 10px;
        }
    }
    h3 {
        font-size: 17px;
        font-weight: 500;
    }
    blockquote {
        position: relative;
        margin: 10px 20px;
        padding: 10px 5px;
        box-sizing: border-box;
        font-style: italic;
        background: rgb(245, 245, 245, 0.5);
        color: #777777;
        border-left: 4px solid rgb(245, 245, 245, 0.9);
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.14);
        font-family: FontAwesome;
        @media screen and (max-width: 768px) {
            margin: 10px 10px;
        }
    }
    blockquote p {
        padding: 0;
        margin: 7px 0;
        line-height: 1.7;
    }
    pre {
        margin: 20px 0;
        padding: 0 20px;
        @media screen and (max-width: 768px) {
            padding: 0 10px;
        }
        div {
            padding: 20px 10px;
            code {
                font-size: 16px;
            }
        }
    }
    p {
        padding: 5px 20px;
        font-size: 17px;
        @media screen and (max-width: 768px) {
            padding: 5px 10px;
        }

        img {
            width: 100%;
        }
    }
    :not(h1, h2) {
        padding: 0 20px;
    }
    table {
        font-family: verdana, arial, sans-serif;
        font-size: 18px;
        color: #333333;
        border-width: 1px;
        border-color: #a9c6c9;
        border-collapse: collapse;
        margin: 10px 20px;
        @media screen and (max-width: 768px) {
            margin: 5px 10px;
        }
    }
    table th {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
    table td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
    table tr:nth-child(odd) {
        background-color: #d4e3e5;
    }
    // table tr:nth-child(even) {
    //     background-color: #c3dde0;
    // }
    .oddrowcolor {
        background-color: #d4e3e5;
    }
    .evenrowcolor {
        background-color: #c3dde0;
    }
}
</style>
