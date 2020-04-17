const baseUrl = process.env.NODE_ENV === 'production' ? './' : '/';
const UselessFile = require('useless-files-webpack-plugin')
// const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;
const glob = require("glob")
const PurifyCSSPlugin = require("purifycss-webpack")
const path = require("path")
let dev = process.env.NODE_ENV === 'production' ? false : true
module.exports = {
    //配置webpack选项
    configureWebpack: {
        plugins: [
            new UselessFile({
                root: './src', // 项目目录
                out: './fileList.json', // 输出文件列表
                clean: false,// 删除文件,
                //  exclude: path // 排除文件列表, 格式为文件路径数组
            }),
            // new BundleAnalyzerPlugin(),
            new PurifyCSSPlugin({
                paths: glob.sync(path.join(__dirname, './src/**/**')),
            }),
        ],
        // devtool:"cheap-module-eval-source-map"
        devtool: dev ? "cheap-module-eval-source-map" : "none",
    },
    // publicPath: process.env.NODE_ENV === 'production' ? './' : '/',
    publicPath: '/',
    outputDir: "dist",
    // assetsDir: "static",
    indexPath: 'index.html',
    runtimeCompiler: true, //关键点在这
    css: {
        loaderOptions: {
            // 给 sass-loader 传递选项
            sass: {
                // @/ 是 src/ 的别名
                // 所以这里假设你有 `src/variables.scss` 这个文件
                //prependData: `@import "~@/assets/scss/variables.scss";`
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