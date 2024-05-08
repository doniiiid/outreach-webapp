import { render, screen } from "@testing-library/react";
import Dashboard from "../dashboard";

describe("<Dashboard /> unit tests", () => {
  test("is page rendered", () => {
    render(<Dashboard />);
    const divElement = screen.getByTestId("container-id");
    expect(divElement).toBeInTheDocument();
  });
});
