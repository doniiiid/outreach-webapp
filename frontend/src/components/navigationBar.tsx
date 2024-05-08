import { Component } from "react";
import { withRouter } from "./withRouter";
import { Button, Select, TextField } from "@mui/material";
import "./navigationBar.css";

interface NavigationProps {
  navigate: (route: string) => void;
  location: any;
}

class NavigationBar extends Component<NavigationProps> {
  // based on the user role stored in the session storage,
  // render a navbar

  // Note: maybe an action bar that shows different types of available items (food, clothing, electronics, furniture, medicine, etc)
  // clicking on will filter the map by NPOs that are requesting these items
  handleSettingsClick = () => {
    const { navigate } = this.props;
    navigate("/settings");
  };

  render = () => {
    return (
      <div data-testid="container-id" className="navigation-container">
        <div>Outreach</div>
        <div>
          <TextField label="Search" />
          <Select label="Location" />
          <Button>Search</Button>
        </div>
        <div className="settings-container">
          <Button onClick={this.handleSettingsClick}>Settings</Button>
        </div>
      </div>
    );
  };
}

export default withRouter(NavigationBar);
