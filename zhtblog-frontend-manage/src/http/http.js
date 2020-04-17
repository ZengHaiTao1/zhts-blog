import qs from 'qs'
//仪表盘请求

/**
 * 获取每月文章数
 */
export function getArticlsByDate() {
    return window.axiosInstance({
        method: 'get',
        url: '/getArticlsByDate',
    }).then((response) => {
        let data = response.data.data;
        let arrData = [];
        for (let i = 0; i < data.length; i++) {
            let obj = data[i]
            for (let key in obj) {
                arrData.push(obj[key])
            }
        }
        return arrData;
    })
        .catch((err) => {
            // console.log(err);
        });
}
// [{
//     categories: "hexo"
// display: "1"
// id: 1
// num: 2
// }]
// 获取分类情况
export function getAllCategories() {
    return window.axiosInstance({
        method: 'get',
        url: '/getCategoriesByDisplay',
    }).then((response) => {
        let data = response.data.data;
        let newData = [];
        for (let i = 0; i < data.length; i++) {
            newData.push({
                value: data[i].num,
                name: data[i].categories
            })
        }
        return newData;
    })
        .catch((err) => {
            // console.log(err);
        });
}

/**
 * 获取访问日志
 * @param  {} id
 */
export function getVisitorByPage(id) {
    return window.axiosInstance({
        method: 'get',
        url: '/getVisitorByPage',
        params: {
            id: parseInt(id)
        }
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            // console.log(err);
        });
}
/**
 * 返回访问人数
 */
export function getVisitorNum() {
    return window.axiosInstance({
        method: 'get',
        url: '/getVisitorNum',
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            // console.log(err);
        });
}

/**
 * 根据分页返回文章列表
 */
export function getArticlsByPage(page) {
    return window.axiosInstance({
        method: 'get',
        url: '/getArticlsByPage',
        params: {
            page: parseInt(page)
        }
    }).then((response) => {
        let data = response.data.data;
        data.map((cur) => {
            if (cur.recommended == "1") {
                cur.recommended = true
            } else {
                cur.recommended = false
            }
            cur.tags = cur.tags.split(",");
        })
        return data;
    })
        .catch((err) => {
            // console.log(err);
        });
    //数据格式
    // {
    //     id: 1,
    //     likearticls: 55,
    //     time: "2016-05-01",
    //     title: "曾海涛",
    //     recommended: true,
    //     browse: 11,
    //     category: "vue"
    // }
}


export function getArticlNum() {
    return window.axiosInstance({
        method: 'get',
        url: '/getArticlNum',
    }).then((response) => {
        return response.data.data;

    })
        .catch((err) => {
            // console.log(err);
        });
}

export function setArticlDisplay(id, tf) {
    return window.axiosInstance({
        method: 'post',
        url: '/setArticlDisplay',
        data: qs.stringify({
            id: id, tf: tf
        })
    }).then((response) => {
        return response.data.data;

    })
        .catch((err) => {
            // console.log(err);
        });
}


/**
 * 发送文章到后台
 * @param  {} data  文章信息
 */
export function Publisharticles(data, draftsArticleId) {
    return window.axiosInstance({
        method: 'post',
        url: '/saveActicle/' + draftsArticleId,
        data: data,
    }).then((response) => {
        let res = response.data;
        return res;
    })
        .catch((err) => {
            // console.log(err);
        });

    //数据格式
    // data = {
    //     title,
    //     html,
    //     tags, []
    //     category,
    //     markdown: this.markdown
    // }
}

/**
 * 发送草稿到后台
 * @param  {} data  文章信息
 */
export function saveDraftsArticles(data) {
    return window.axiosInstance({
        method: 'post',
        url: '/savedrAftsArticles',
        data: data
    }).then((response) => {
        let res = response.data;
        return res;
    })
        .catch((err) => {
            // console.log(err);
        });
}
/**
 * 修改文章
 * @param  {} data  文章信息
 */
export function updateArticle(data) {
    return window.axiosInstance({
        method: 'post',
        url: '/updateArticle',
        data: data
    }).then((response) => {
        let res = response.data;
        return res;
    })
        .catch((err) => {
            // console.log(err);
        });
}

export function getDraftsArticleById(id) {
    return window.axiosInstance({
        method: 'get',
        url: '/getDraftsArticleById',
        params: {
            id: parseInt(id)
        }
    }).then((response) => {
        let data = response.data.data;
        return data;

    }).catch((err) => {
        // console.log(err);
    });
}

/**
 * 获取所有草稿
 */
export function getAllDraftsarticles() {
    return window.axiosInstance({
        method: 'get',
        url: '/getAllDraftsarticles',
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            // console.log(err);
        });
}

/**
 * 根据id删除草稿
 */
export function deleteDraftsarticlesById(id) {
    return window.axiosInstance({
        method: 'post',
        url: '/deleteDraftsarticlesById',
        data: qs.stringify({
            id
        })
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            // console.log(err);
        });
}


export function getArticlsById(id) {
    return window.axiosInstance({
        method: 'get',
        url: '/getArticlsById',
        params: {
            id
        }
    }).then((response) => {
        let data = response.data.data;
        data.tags = data.tags.split(",");
        return data;

    }).catch((err) => {
        // console.log(err);
    });
}

/**
 * 修改公告
 * @param  {} data
 */
export function updateNotice(notice) {
    return window.axiosInstance({
        method: 'post',
        url: '/updateNotice',
        data: qs.stringify({ notice })
    }).then((response) => {
        return response.data;
    })
        .catch((err) => {
            // console.log(err);
        });
}

/**
 * 获取公告
 * @param  {} 
 */
export function getNotice() {
    return window.axiosInstance({
        method: 'get',
        url: '/getNotice',
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            // console.log(err);
        });
}



/**
 * 改变标签在首页的显示状态
 * @param  {} obj
 */
export function updateTagDisplay(id, tf) {
    return window.axiosInstance({
        method: 'post',
        url: '/updateTagDisplay',
        data: qs.stringify({
            id, tf
        })
    }).then((response) => {
        return response.data;
    })
        .catch((err) => {
            // console.log(err);
        });
}
/**
 * 获取所有标签
 */
export function getAllTags() {
    return window.axiosInstance({
        method: 'get',
        url: '/getLableByDisplay',
    }).then((response) => {
        let data = response.data.data;
        data.map((cur) => {
            if (cur.display == "1") {
                cur.display = true
            } else {
                cur.display = false
            }
        })
        return data;
    })
        .catch((err) => {
            // console.log(err);
        });
}
/**
 * 获取所有分类
 */
export function getAllCategoriesPage() {
    return window.axiosInstance({
        method: 'get',
        url: '/getCategoriesByDisplay',
    }).then((response) => {
        let data = response.data.data;
        data.map((cur) => {
            if (cur.display == "1") {
                cur.display = true
            } else {
                cur.display = false
            }
            cur.name = cur.categories
        })
        return data;
    })
        .catch((err) => {
            // console.log(err);
        });
}

/**
 * 改变分类在首页的显示状态
 * @param  {} obj
 */
export function updateCategoriesDisplay(id, tf) {
    return window.axiosInstance({
        method: 'post',
        url: '/updateCategoriesDisplay',
        data: qs.stringify({
            id, tf
        })
    }).then((response) => {
        return response.data;
    })
        .catch((err) => {
            // console.log(err);
        });
}

export function login(username, password, token) {
    return window.axiosInstance({
        method: 'post',
        url: '/Login',
        data: qs.stringify({
            username, password, token
        })
    }).then((response) => {
        let data = response.data;
        console.log(data)
        console.log(data.status == 200)
        if (data.status == 200) {
            let storage = window.localStorage;
            storage.setExpire("zhtblog_token", data.data, 1000 * 60 * 60 * 24)
            window.axiosInstance.defaults.headers.common['token'] = data.data;
        }
        return data;
    })
        .catch((err) => {
            // console.log(err);
        });
}

export function loginOut() {
    return window.axiosInstance({
        method: 'post',
        url: '/LoginOut',
        data: qs.stringify({
            token: window.localStorage.getExpire("zhtblog_token")
        })
    }).then((response) => {
        return response.data;
    })
        .catch((err) => {
            // console.log(err);
        });
}

export function token() {
    return window.axiosInstance({
        method: 'post',
        url: '/token',
        data: qs.stringify({
            token: window.localStorage.getExpire("zhtblog_token")
        })
    }).then((response) => {
        return response.data;
    })
        .catch((err) => {
            // console.log(err);
        });
}