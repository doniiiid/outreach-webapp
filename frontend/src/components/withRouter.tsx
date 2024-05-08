import { useLocation, useNavigate } from "react-router-dom";

export function withRouter(Children: any) {
  return (props: any) => {
    const location = useLocation();
    const navigate = useNavigate();

    return <Children {...props} location={location} navigate={navigate} />;
  };
}
