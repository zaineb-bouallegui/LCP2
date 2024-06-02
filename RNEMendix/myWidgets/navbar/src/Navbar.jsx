import { createElement } from "react";

import { HelloWorldSample } from "./components/HelloWorldSample";
import "./ui/Navbar.css";

export function Navbar({ sampleText }) {
    return <HelloWorldSample sampleText={sampleText} />;
}
