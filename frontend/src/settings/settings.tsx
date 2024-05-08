import { Component } from "react";
import LogoutButton from "../components/logoutButton";
import { Button } from "@mui/material";
import { withRouter } from "../components/withRouter";

interface SettingsProps {
  navigate: (route: string) => void;
  location: any;
}
/**
 * Allow for updating account information
 *
 *
 */
class Settings extends Component<SettingsProps> {
  handleReturn = () => {
    const { navigate } = this.props;
    navigate("/dashboard");
  };
  render = () => {
    return (
      <div data-testid="container-id">
        <Button onClick={this.handleReturn}>Return</Button>
        <LogoutButton />
      </div>
    );
  };
}

export default withRouter(Settings);
