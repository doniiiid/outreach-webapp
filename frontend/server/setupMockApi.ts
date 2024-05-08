import mockAppApi from "./mockAppApi";

const setupMockServer = function setupMockServer(app: any) {
  mockAppApi(app);
};
export default setupMockServer;
