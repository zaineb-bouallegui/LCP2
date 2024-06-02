import { createElement } from "react";

import { HelloWorldSample } from "./components/HelloWorldSample";
import "./ui/SideBar.css";

export function SideBar({ sampleText }) {
    return <HelloWorldSample sampleText={sampleText} />;
}
