const plugins = [];
plugins.push([
  "component",
  {
    "libraryName": "element-ui",
    "styleLibraryName": "theme-chalk"
  }
])
if (process.env.NODE_ENV === 'production') {
  plugins.push('transform-remove-console')
}
module.exports = {
  presets: [
    '@vue/app'
  ],
  plugins
  // ignore: [
  //   './src/assets/js/*.js'
  // ]
}
