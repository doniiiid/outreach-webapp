declare module "*.png";
declare module "*.svg";
declare module "*.jpeg";
declare module "*.jpg" {
  const value: any;
  export default value;
}
