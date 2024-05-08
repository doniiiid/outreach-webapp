import { Component, ReactNode } from "react";

interface LocalActivityFeedProps {
  data: Array<{
    id: string;
    name: string;
    comment: string;
    img: File | null;
    date: string;
    numberOfDonations: number;
  }>;
}

export default class LocalActivityFeed extends Component<LocalActivityFeedProps> {
  render = () => {
    const { data } = this.props;
    console.log("data:", data);
    return (
      <div className="activity-feed-container">
        <h1 className="activity-feed-title">Local Activity</h1>
        <div className="acivity-details-container">
          {data.length > 0 &&
            data.map(({ id, name, date, numberOfDonations }) => (
              <div className="activity-detail">
                <div className="detail-header">
                  <img className="detail-profile-picture" alt="profile-pic" />
                  <div className="detail-profile-name">
                    <h3>{name}</h3>
                    <div className="detail-date">{date}</div>
                  </div>
                </div>
                <div className="detail-description">
                  Number of donations accepted: {numberOfDonations}
                </div>
              </div>
            ))}
        </div>
      </div>
    );
  };
}
