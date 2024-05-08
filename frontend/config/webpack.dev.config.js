const { merge } = require("webpack-merge");
const baseConfig = require("../webpack.config");

module.exports = merge(baseConfig, {
  mode: "development",
  devtool: "inline-source-map",
  devServer: {
    historyApiFallback: true,
    static: "./dist",
    hot: true,
    open: true,
    port: 5001,
    client: {
      logging: "info",
      overlay: {
        errors: true,
        warnings: false,
        runtimeErrors: true,
      },
    },
    proxy: {
      "/api": {
        target: "http://localhost:3000",
        secure: false,
      },
    },
  },
});
