import { Component } from "react";
import { withRouter } from "./withRouter";
import { Carousel as ResponsiveCarousel } from "react-responsive-carousel";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import "./localActivityFeed.css";

interface CarouselProps {
  navigate: (route: string) => void;
  location: any;
  images: Array<string>;
}

class Carousel extends Component<CarouselProps> {
  handleGetStarted = () => {
    /** Handle navigating to search screen with default search */
  };

  render = () => {
    const { images } = this.props;
    return (
      <div className="carousel-container">
        <ResponsiveCarousel
          autoPlay
          infiniteLoop
          showThumbs={false}
          stopOnHover={false}
        >
          {...images.map((url: string, index: number) => (
            <div>
              <img src={url} alt={`${index}`} />
            </div>
          ))}
        </ResponsiveCarousel>
      </div>
    );
  };
}

export default withRouter(Carousel);
