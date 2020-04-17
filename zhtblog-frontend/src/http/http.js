import qs from 'qs'
/**
 * 获取在主页显示的lable数据
 */
export function getLableByDisplay() {
    return window.axiosInstance({
        method: 'get',
        url: '/getLableByDisplay',
        params: { display: 1 }
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            console.log(err);
        });
    // 数据格式
    // [{
    //     href:"",
    //     name:""
    //      },]
}

/**
 * 获取所有标签数据
 */
export function getAllLable() {
    return window.axiosInstance({
        method: 'get',
        url: '/getLableByDisplay',
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            console.log(err);
        });
    // 数据格式
    // [{
    //     href:"",
    //     name:""
    //      },]
}


/**
 * 获取网站描述信息，已经文章数量等
 */
export function getBlogSummary() {
    return window.axiosInstance({
        method: 'get',
        url: '/getBlogSummary',
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            console.log(err);
        });
    // 数据格式
    // {
    //     description:"",
    //     postnum:"",
    //    tagnum:"",
    //    categoriesnum:""
    // }
}

/**
 * 获取网站公告
 */
export function getNotice() {
    return window.axiosInstance({
        method: 'get',
        url: '/getNotice',
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            console.log(err);
        });
    // 数据格式
    // {
    //     description:"",
    //     postnum:"",
    //    tagnum:"",
    //    categoriesnum:""
    // }
}

/**
 * 获取推荐阅读
 */
export function getRecommendedReading() {
    return window.axiosInstance({
        method: 'get',
        url: '/getArticlsByReading',
        // data: { reading: 1 }
        params: {
            reading: "1"
        }
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            console.log(err);
        });
    // 数据格式
    //    [{   
    // href: "#",
    // title: "阿里巴巴面经记222",
    // date: "2019-11-28",
    // readnum: "30",
    // like: "113"
    //     }]
}


/**
 * 获取主页显示的分类信息
 */
export function getCategories() {
    return window.axiosInstance({
        method: 'get',
        url: '/getCategoriesByDisplay',
        params: {
            display: 1
        }
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            console.log(err);
        });
    // 数据格式
    //       [{   
    // href: "#",
    // name: "es6",
    // num: "12"
    //        }]
}


/**
 * 获取主页显示的分类信息
 */
export function getAllCategories() {
    return window.axiosInstance({
        method: 'get',
        url: '/getCategoriesByDisplay',
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            console.log(err);
        });
    // 数据格式
    //       [{   
    // href: "#",
    // name: "es6",
    // num: "12"
    //        }]
}

/**
 * h获取网站资讯
 */
export function getBloginfo() {
    let data;
    window.axiosInstance({
        method: 'get',
        url: '/Bloginfo',
    }).then((response) => {
        data = response.data;
    })
        .catch((err) => {
            console.log(err);
        });
    return data;
    // 数据格式
    //       [{   
    //     name: "文章数目",
    //     num: "25"
    //        }]
}

export function addBrowse(id) {
    return window.axiosInstance({
        method: 'get',
        url: '/addBrowse',
        params: {
            id
        }
    }).then((response) => {
        return response.data;
    })
        .catch((err) => {
            console.log(err);
        });
}

export function addLikearticls(id) {
    return window.axiosInstance({
        method: 'get',
        url: '/addLikearticls',
        params: {
            id
        }
    }).then((response) => {
        return response.data;
    })
        .catch((err) => {
            console.log(err);
        });
}

/**
 * @param  {Number} Pagenum 获取多少页的数据，点击加载更多时，进行加载更多的数据
 */
export function getArticlelistByPage(page) {
    return window.axiosInstance({
        method: 'get',
        url: '/getArticlsByPage',
        params: {
            page
        }
    }).then((response) => {
        let res = response.data.data;
        res.map((currentValue) => {
            currentValue.tags = currentValue.tags.split(",");
            currentValue.description =
                currentValue.html.replace(/<.*?>/g, "").substring(0, 50) +
                "...";
        });
        return res;
    })
        .catch((err) => {
            console.log(err);
        });

    // 数据格式
    // articleData: [
    //     {
    //         title: "阿里巴巴面经记1111",
    //         href: "#",
    //         Tags: [
    //             {
    //                 name: "webpack",
    //                 href: "#"
    //             },
    //         ],
    //         description: "描述",
    //         date: "2019-12-16",
    //         Read: "333",
    //         like: "444"
    //     }
    // ]
}

/**
 * 获取时间轴数据
 */
export function getTimeAxis() {
    return window.axiosInstance({
        method: 'get',
        url: '/getTimeAxis',
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            console.log(err);
        });
    // 数据格式
    // articleData: [
    //     {
    //         year: 2019,
    //         num: 61,
    //         month: [
    //             {
    //                 monthTitle: 11,
    //                 num: 4,
    //                 articlelist: [
    //                     {
    //                         data: "2019-11-28",
    //                         title: "阿里巴巴面经记",
    //                         href: "#"
    //                     }
    //                 ]
    //             },
    //         ]
    //     }
    // ]
}

/**
 * 根据标签获取时间轴
 * @param  {} tag
 */
export function getTimeAxisByTag(tag) {
    return window.axiosInstance({
        method: 'get',
        url: '/getTimeAxis',
        params: {
            tag
        }
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            console.log(err);
        });
}

/**
 * 根据分类获取时间轴
 * @param  {} tag
 */
export function getTimeAxisByCategories(categorie) {
    return window.axiosInstance({
        method: 'get',
        url: '/getTimeAxis',
        params: {
            categorie
        }
    }).then((response) => {
        return response.data.data;
    })
        .catch((err) => {
            console.log(err);
        });
}

/**
 * @param  {number} id  根据文章的id获取数据
 */
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
        console.log(err);
    });
    //数据格式
    // article: {
    //     title: "阿里巴巴面经记1111",
    //     href: "#",
    //     html:"",
    //     categories: [
    //         {
    //             name: "webpack",
    //             href: "#"
    //         },
    //     ],
    //     date: "2019-12-16",
    //     Read: "333",
    //     like: "444"
    // }
}


export function addVisitor(data) {
    return window.axiosInstance({
        method: 'post',
        url: '/addVisitor',
        data: qs.stringify(data),
    }).then((response) => {
        return response.data.data;
    }).catch((err) => {
        console.log(err);
    })
}

export function getBrowseAndLike(id) {
    return window.axiosInstance({
        method: 'get',
        url: '/getBrowseAndLike',
        params: {
            id
        }
    }).then((response) => {
        return response.data.data;
    }).catch((err) => {
        console.log(err);
    })
}
/**
 * 根据id获取标签名
 * @param  {} id
 */
export function getTagNameById(id) {
    return window.axiosInstance({
        method: 'get',
        url: '/getTagNameById',
        params: {
            id
        }
    }).then((response) => {
        return response;
    }).catch((err) => {
        console.log(err);
    })
}

/**
 * 根据id获取分类名
 * @param  {} id
 */
export function getCategoriesNameById(id) {
    return window.axiosInstance({
        method: 'get',
        url: '/getCategoriesNameById',
        params: {
            id
        }
    }).then((response) => {
        return response;
    }).catch((err) => {
        console.log(err);
    })
}