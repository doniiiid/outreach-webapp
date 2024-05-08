import express from "express";
import webpack from "webpack";
import webpackDevMiddleware from "webpack-dev-middleware";
import webpackHotMiddleware from "webpack-hot-middleware";
import setupMockServer from "./setupMockApi";
import path from "path";
import fs from "fs";

const app = express();
const config = require("../config/webpack.dev.config.js");
const compiler = webpack(config);

// Tell express to use the webpack-dev-middleware and use the webpack.dev.config.js
// configuration file as a base.
// app.use(
//   webpackDevMiddleware(compiler, {
//     publicPath: config.output.publicPath,
//     stats: {
//       chunks: false,
//       hash: false,
//       modules: false,
//       version: false,
//       assets: false,
//       entrypoints: false,
//       builtAt: false,
//     },
//   })
// );

// app.use(webpackHotMiddleware(compiler));

// app.use(express.static("./dist"));

//Set up mock api
setupMockServer(app);

//serve the routes
// app.get("*", (req: any, res: any, next: any) => {
//   fs.readFile(
//     path.join(compiler.outputPath, "./dist/index.html"),
//     (err, file) => {
//       if (err) {
//         res.sendStatus(404);
//       } else {
//         res.send(file.toString());
//       }
//     }
//   );
// });

// Serve the files on port 3000.
app.listen(3000, function () {
  console.log("Mock outreach app listening on port 3000!\n");
});
