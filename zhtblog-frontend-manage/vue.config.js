const baseUrl = process.env.NODE_ENV === 'production' ? './' : '/';
const path = require("path")
const dev = process.env.NODE_ENV === 'production' ? false : true
module.exports = {

    lintOnSave: false,
    transpileDependencies: [
        'vue-echarts',
        'resize-detector'
    ],
    //配置webpack选项
    configureWebpack: {
        devtool: dev ? "cheap-module-eval-source-map" : "none",
    },
    // publicPath: process.env.NODE_ENV === 'production' ? './' : '/',
    publicPath: '/admin/',
    outputDir: "dist",
    // assetsDir: "static",
    indexPath: 'index.html',
    runtimeCompiler: true, //关键点在这
    css: {
        loaderOptions: {
            sass: {
                prependData: `$baseUrl: "${baseUrl}";`
            },
        }
    },
    devServer: {
        host: "localhost",
        port: 8080, // 端口号
        https: false, // https:{type:Boolean}
        // open: true, //配置自动启动浏览器
        // proxy: 'http://localhost:4000' // 配置跨域处理,只有一个代理
        // 配置多个代理
        proxy: {
            "/api": {
                // target: "http://localhost:8081/",// 要访问的接口域名
                target: "http://49.234.222.203:8081/",// 要访问的接口域名
                // ws: true,// 是否启用websockets
                changeOrigin: true, //开启代理：在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
                pathRewrite: {
                    '^/api': '' //这里理解成用'/api'代替target里面的地址,比如我要调用'http://40.00.100.100:3002/user/add'，直接写'/api/user/add'即可
                }
            },
        }
    }
}