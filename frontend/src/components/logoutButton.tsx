import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

const LogoutButton = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Step 1: Clear the JWT
    localStorage.removeItem("accessToken");

    // Step 2: Update any application state (not shown here)

    // Step 3: Redirect to login
    navigate("/login");
  };

  return <Button onClick={handleLogout}>Logout</Button>;
};

export default LogoutButton;
