import React, { Component, useEffect } from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
  useNavigate,
  Outlet,
} from "react-router-dom";
import "./App.css";
import Login from "./login/login";
import Dashboard from "./dashboard/dashboard";
import Settings from "./settings/settings";

const App = () => {
  const isAuthenticated = () => {
    let token = localStorage.getItem("accessToken");

    return !!token;
  };

  const PrivateRoutes = () => {
    if (process.env.NODE_ENV === "development") {
      localStorage.setItem(
        "accessToken",
        "your_mock_jwt_here.qeeqff232rqr.3qrqff3fq"
      );
    }
    const auth = isAuthenticated();

    return auth ? <Outlet /> : <Navigate to="/login" replace />;
  };

  return (
    <Router>
      <Routes>
        <Route path="/" element={<PrivateRoutes />}>
          <Route
            index={isAuthenticated()}
            path="/dashboard"
            element={<Dashboard />}
          />
          <Route path="/settings" element={<Settings />} />
        </Route>
        <Route path="/login" element={<Login />} />
        <Route
          path="*"
          element={
            isAuthenticated() ? (
              <Navigate to="/dashboard" replace />
            ) : (
              <Navigate to="/" replace />
            )
          }
        />
      </Routes>
    </Router>
  );
};

export default App;
