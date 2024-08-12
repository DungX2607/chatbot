const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 3000
  },
  lintOnSave: true, // Đảm bảo ESLint được bật
  pluginOptions: {
    eslintConfig: {
      rules: {
        'vue/multi-word-component-names': 'off'
      }
    }
  }
})
