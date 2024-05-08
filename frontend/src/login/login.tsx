import { Component } from "react";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import axios from "axios";
import { withRouter } from "../components/withRouter";

interface LoginProps {
  navigate: (route: string) => void;
  location: any;
}

class Login extends Component<LoginProps> {
  constructor(props: { navigate: (route: string) => void; location: any }) {
    super(props);
    const DEFAULT_ROLE = "user";

    this.state = {
      username: null,
      password: null,
      form: {
        role: DEFAULT_ROLE,
        email: null,
        firstName: null,
        lastName: null,
      },
      isShowingSignupForm: false,
    };
    this.handleUsernameChange = this.handleChange.bind(this, "username");
    this.handlePasswordChange = this.handleChange.bind(this, "password");
    this.handleRoleChange = this.handleFormChange.bind(this, "role");
    this.handleEmailChange = this.handleFormChange.bind(this, "email");
    this.handleFirstNameChange = this.handleFormChange.bind(this, "firstName");
    this.handleLastNameChange = this.handleFormChange.bind(this, "lastName");
  }

  state: {
    username: string | null;
    password: string | null;
    form: {
      role: string;
      email: string | null;
      firstName: string | null;
      lastName: string | null;
    };
    isShowingSignupForm: boolean;
  };

  handleUsernameChange: (value: string, _: this) => void;

  handlePasswordChange: (value: string, _: this) => void;

  handleRoleChange: (value: string, _: this) => void;

  handleEmailChange: (value: string, _: this) => void;

  handleFirstNameChange: (value: string, _: this) => void;

  handleLastNameChange: (value: string, _: this) => void;

  handleChange = (input: string, value: string) => {
    this.setState({ [input]: value });
  };

  handleFormChange = (input: string, value: string) => {
    const { form: prevForm } = this.state;
    this.setState({ form: { ...prevForm, [input]: value } });
  };

  toggleSignUpForm = () => {
    const { isShowingSignupForm: prevIsShowingForm } = this.state;

    this.setState({
      isShowingSignupForm: !prevIsShowingForm,
      ...(!prevIsShowingForm
        ? {
            form: {
              role: "user",
              email: null,
              firstName: null,
              lastName: null,
            },
          }
        : {}),
    });
  };

  handleSignin = async () => {
    const { username, password } = this.state;
    const { navigate } = this.props;
    const payload = { username, password };

    await axios.post("/api/auth/signin", payload).then((response) => {
      const {
        data: { accessToken, roles, tokenType },
      } = response;
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("roles", roles);
      localStorage.setItem("tokenType", tokenType);
      navigate("/dashboard");
    });
  };

  handleSignup = async () => {
    const { form, username, password } = this.state;
    const payload = { ...form, username, password };

    await axios.post("/api/auth/signup", payload).then(
      (response) => {
        this.handleSignin();
      },
      (onRejected) => {
        // Display error modal
      }
    );
  };

  renderSignupForm = () => {
    const {
      isShowingSignupForm,
      form: { role },
    } = this.state;
    return (
      <Dialog open={isShowingSignupForm} onClose={this.toggleSignUpForm}>
        <DialogTitle>Sign Up</DialogTitle>
        <DialogContent>
          <Select
            label="Role"
            value={role}
            onChange={({ target: { value } }) =>
              this.handleRoleChange(value, this)
            }
          >
            <MenuItem value="user">User</MenuItem>
            <MenuItem value="npo">NPO</MenuItem>
            <MenuItem value="store">Store</MenuItem>
          </Select>
          {role === "user" && (
            <>
              <TextField
                label="Email"
                onChange={({ target: { value } }) => {
                  this.handleEmailChange(value, this);
                }}
              />
              <TextField
                label="Username"
                onChange={({ target: { value } }) => {
                  this.handleUsernameChange(value, this);
                }}
              />
              <TextField
                label="Password"
                onChange={({ target: { value } }) => {
                  this.handlePasswordChange(value, this);
                }}
              />
            </>
          )}
        </DialogContent>
        <DialogActions>
          <Button onClick={this.toggleSignUpForm}>Cancel</Button>
          <Button type="submit" onClick={this.handleSignup}>
            Create Account
          </Button>
        </DialogActions>
      </Dialog>
    );
  };

  render = () => {
    const { isShowingSignupForm } = this.state;
    return (
      <div data-testid={"container-id"}>
        {isShowingSignupForm && this.renderSignupForm()}
        <TextField
          id="outlined-basic"
          label="Username"
          variant="outlined"
          onChange={({ target: { value } }) => {
            this.handleUsernameChange(value, this);
          }}
        />
        <TextField
          id="outlined-basic"
          label="Password"
          variant="outlined"
          onChange={({ target: { value } }) => {
            this.handlePasswordChange(value, this);
          }}
        />
        <Button
          onClick={() => {
            this.handleSignin();
          }}
        >
          Sign In
        </Button>
        <Button
          onClick={() => {
            this.toggleSignUpForm();
          }}
        >
          Sign Up
        </Button>
      </div>
    );
  };
}

export default withRouter(Login);
