import React from "react";
import { render, screen } from "@testing-library/react";
import Login from "../login";

describe("<Login /> unit tests", () => {
  test("is page rendered", () => {
    render(<Login />);
    const divElement = screen.getByTestId("container-id");
    expect(divElement).toBeInTheDocument();
  });
});
