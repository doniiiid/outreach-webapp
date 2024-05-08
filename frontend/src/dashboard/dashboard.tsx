import { Component } from "react";
import NavigationBar from "../components/navigationBar";
import { withRouter } from "../components/withRouter";
import axios from "axios";
import Carousel from "../components/carousel";
import image1 from "./../../public/images/image1.jpg";
import image2 from "./../../public/images/image2.jpg";
import image3 from "./../../public/images/image3.jpg";
import LocalActivityFeed from "../components/localActivityFeed";

class Dashboard extends Component {
  state: {
    urls: Array<string>;
    activityData: Array<{
      id: string;
      name: string;
      comment: string;
      img: File | null;
      date: string;
      numberOfDonations: number;
    }>;
  } = { urls: [], activityData: [] };

  componentDidMount(): void {
    this.fetchCaroselImgaes();
    this.fetchLocalActivity();
  }

  fetchLocalActivity = async () => {
    axios.get("/api/dashboard/region/activity").then((response) => {
      const {
        data: { data },
      } = response;

      this.setState({ activityData: data });
    });
  };

  fetchCaroselImgaes = async () => {
    axios.get("/api/dashboard/images").then((response) => {
      const {
        data: { urls },
      } = response;
      this.setState({ urls: [image1, image2, image3] });
    });
  };

  render = () => {
    const { urls, activityData } = this.state;
    return (
      <div data-testid="container-id">
        <NavigationBar />
        <Carousel images={urls} />
        <LocalActivityFeed data={activityData} />
      </div>
    );
  };
}

export default withRouter(Dashboard);
