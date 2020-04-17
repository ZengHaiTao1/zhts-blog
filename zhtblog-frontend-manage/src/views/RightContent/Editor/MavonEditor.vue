<template>
    <div class="MavonEditor">
        <div class="title">
            <el-input placeholder="标题" v-model="title" clearable></el-input>
        </div>
        <mavon-editor
            ref="editor"
            :ishljs="true"
            v-model="doc"
            @change="updateDoc"
            @save="saveDoc"
            class="editor"
            @imgAdd="$imgAdd"
        ></mavon-editor>
        <EleTag @getTag="getTag" :dynamicTags="Tags"></EleTag>
        <el-input class="category" placeholder="请输入内容" v-model="category">
            <template slot="prepend">分类</template>
        </el-input>
        <div class="postArticle">
            <el-button :disabled="inputdisabled" type="primary" class="btn" @click="Submission">
                发布
                <i class="el-icon-upload el-icon--right"></i>
            </el-button>
        </div>
    </div>
</template>
<script>
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";

import {
    Publisharticles,
    saveDraftsArticles,
    updateArticle,
    getDraftsArticleById
} from "@/http/http.js";
import { getOffset, getScrollTop, throttle } from "@/utils/utils.js";
import { mapActions } from "vuex";
export default {
    name: "Create",
    components: {
        mavonEditor,
        EleTag: () => import("@/views/RightContent/Editor/EleTag/EleTag.vue")
    },
    data() {
        return {
            doc: "",
            html: "",
            markdown: "",
            inputdisabled: false,
            title: "",
            category: "",
            Tags: [],
            id: null,
            updateArticle: false,
            draftsArticleId: null
        };
    },
    methods: {
        /*
             获取 markdown
            let markdown = this.$refs.editor.d_value;
             修改 markdown
            this.$refs.editor.d_value = '> hello world';
            // 获取编译后的 html
            let html = this.$refs.editor.d_render;
            使用 v-model
             获取 markdown
            let doc = this.doc;
            修改  markdown
            this.doc = 'hello world';
            注: 使用 $refs 的方式获取值可以获取到 markdown 和 编译后的 html, 但是使用 v-model 只能获取到 markdown
        */
        open2(meg) {
            this.$message({
                showClose: false,
                message: meg,
                duration: 2000,
                center: true,
                type: "success",
                offset: 200
            });
        },
        // updateDoc(markdown, html) {
        updateDoc(markdown, html) {
            // 此时会自动将 markdown 和 html 传递到这个方法中
            this.html = html;
            this.markdown = markdown;
        },
        // saveDoc(markdown, html) {
        saveDoc() {
            let title = this.title;
            if (title === "") {
                this.inputdisabled = true;
                this.open("保存草稿失败，标题不能为空");
                return;
            }
            saveDraftsArticles({
                id: this.draftsArticleId,
                title: this.title,
                markdown: this.markdown,
                tags: this.Tags.toString(),
                category: this.category
            }).then(res => {
                // console.log(res);
                if (res.status == "200") {
                    this.open2("保存到草稿箱成功");
                    this.draftsArticleId = res.data;
                }
            });
        },
        // 绑定@imgAdd event
        $imgAdd(pos, $file) {
            // console.log("----------------");
            // 第一步.将图片上传到服务器.
            let formdata = new FormData();
            formdata.append("file", $file);
            window
                .axiosInstance({
                    url: "/tengxun",
                    method: "post",
                    data: formdata,
                    headers: { "Content-Type": "multipart/form-data" }
                })
                .then(url => {
                    // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
                    /**
                     * $vm 指为mavonEditor实例，可以通过如下两种方式获取
                     * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
                     * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
                     */
                    // $vm.$img2Url(pos, url);
                    this.$refs.editor.$img2Url(pos, url.data.data);
                    // console.log(url);
                });
        },
        Submission() {
            let title = this.title;
            if (title === "") {
                this.inputdisabled = true;
                this.open("标题不能为空");
                return;
            }
            let html = this.html;
            if (html === "") {
                this.inputdisabled = true;
                this.open("内容不能为空");
                return;
            }
            let tags = this.Tags;
            if (tags.length === 0) {
                this.inputdisabled = true;
                this.open("请输入标签");
                return;
            }
            let category = this.category;
            if (category === "") {
                this.inputdisabled = true;
                this.open("请输入类别");
                return;
            }
            this.$confirm("点击确定发布该文章?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "info",
                center: true,
                lockScroll: true
            })
                .then(() => {
                    let data = {
                        title,
                        html,
                        tags: tags.toString(),
                        category,
                        markdown: this.markdown
                    };
                    // console.log(data);
                    if (this.id != null && this.updateArticle) {
                        data.id = this.id;
                        updateArticle(data).then(res => {
                            if (res.status != "200") {
                                this.open2("服务器异常");
                                return;
                            }
                            this.title = "";
                            this.html = "";
                            this.Tags = [];
                            this.category = "";
                            this.doc = "";
                            this.id = null;
                            this.updateArticle = false;
                            this.open2("修改成功");
                        });
                        return;
                    }
                    Publisharticles(data, this.draftsArticleId).then(res => {
                        if (res.status == "200") {
                            this.open2("发布成功");
                            this.draftsArticleId = null;
                            this.title = "";
                            this.html = "";
                            this.Tags = [];
                            this.category = "";
                            this.doc = "";
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
        },
        openbutton() {
            this.inputdisabled = false;
        },
        getTag(param) {
            this.Tags = param;
        },
        ...mapActions(["getArticeListById"]),
        addfixed() {
            // let ele = document.getElementsByClassName("v-note-op")[0];
            let ele = document.getElementsByClassName("v-note-op")[0];
            let top = getOffset(ele).top;
            return throttle(() => {
                if (getScrollTop() > top) {
                    ele.style.position = "fixed";
                    ele.style.top = 0;
                    ele.style.left = 0;
                } else {
                    ele.style.position = "";
                    ele.style.top = "";
                    ele.style.left = "";
                }
            }, 200);
        }
    },
    created() {
        // console.log(this.$route);
        let fullPath = this.$route.fullPath;
        let id = this.$route.params.id;
        if (fullPath.indexOf("setArticle") != -1) {
            this.updateArticle = true;
            this.getArticeListById(id).then(res => {
                this.id = res.id;
                this.Tags = res.tags;
                this.doc = res.markdown;
                this.markdown = res.markdown;
                this.category = res.category;
                this.title = res.title;
                this.html = res.html;
            });
        } else if (fullPath.indexOf("setDraftsArticle") != -1) {
            this.draftsArticleId = id;
            getDraftsArticleById(id).then(res => {
                // console.log(res);
                this.Tags = res.tags.split(",");
                this.doc = res.markdown;
                this.markdown = res.markdown;
                this.category = res.category;
                this.title = res.title;
            });
        }
        this.$nextTick(() => {
            let ele = document.getElementsByClassName("v-note-op")[0];
            // ele.style.position = "fixed";
            // ele.style.top = 0;
            console.log(getOffset(ele));
            console.log(ele.style.position);
        });
    },
    mounted() {
        window.addEventListener("scroll", this.addfixed(), false);
    }
};
</script>
<style lang="scss" scope>
.editor {
    width: 100%;
    margin: 0 auto;
    position: relative;
    z-index: 0;
}
.toolbar {
    border: 1px solid #ccc;
}
.text {
    border: 1px solid #ccc;
    min-height: 500px;
}
.MavonEditor {
    padding: 20px 20px;
    .editor {
        margin-top: 20px;
    }
    .category {
        margin-bottom: 10px;
        width: 500px;
    }
    .postArticle {
        text-align: center;
        margin: 10px 0;
        .btn {
            width: 200px;
        }
    }
}
</style>