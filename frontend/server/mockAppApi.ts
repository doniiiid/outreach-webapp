import signinData from "./data/signinData";
// import image from "./../public/images/image1.jpg";

const apiRoutes = (app: any) => {
  app.get("/test", (req: any, res: any) => {
    res.json({ data: "Hello World!" });
  });

  // Api for authenticating user
  app.post("/api/auth/signin", (req: any, res: any) => {
    res.json({ ...signinData });
  });

  // Api for terminating session
  app.post("/auth/logout", (req: any, res: any) => {
    res.json({ data: "Hello World!" });
  });

  // Api for signing up as a new user
  app.post("/api/auth/signup", (req: any, res: any) => {
    res.json({ data: "Hello World!" });
  });

  // Api for fetching dashboard carousel
  app.get("/api/dashboard/images", (req: any, res: any) => {
    // Return the paths of the sample images
    // console.log(image);
    const urls = [
      "/Users/doni/Programs/outreach-webapp/frontend/public/images/pexels-laurisrozentals-3302183.jpg",
      "/Users/doni/Programs/outreach-webapp/frontend/public/images/pexels-pixabay-416405.jpg",
      "/Users/doni/Programs/outreach-webapp/frontend/public/images/pexels-rdne-6646862.jpg",
    ];

    res.json({ urls });
  });

  // Api for fetching activity in region
  app.get("/api/dashboard/region/activity", (req: any, res: any) => {
    const data: Array<{
      id: string;
      name: string;
      comment: string;
      img: File | null;
      date: string;
      numberOfDonations: number;
    }> = [
      {
        id: "0",
        name: "Red Cross",
        comment: "",
        img: null,
        date: "05/07/2024",
        numberOfDonations: 37,
      },
    ];

    res.json({ data });
  });
};

export default apiRoutes;
