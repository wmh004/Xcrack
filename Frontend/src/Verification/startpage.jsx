import React, { useState, useEffect } from "react";
import Body from "./body/body";
import XcrackIcon from "../resources/twitter/xcrack-logo.jpg";
import "./startpagestyles.css";
import { Routes, Route } from "react-router-dom";
import VerificationTemplate from "./verificationtemplate/verificationtemplate";

const StartPage = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);

  useEffect(() => {
    const handleResize = () => {
      setWindowWidth(window.innerWidth);
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  return (
    <Routes>
      <Route path="/" element={<Body />} />
      <Route path="login" element={<Body />} />
      <Route path="user/*" element={<VerificationTemplate />} />
    </Routes>
  );
};
export default StartPage;
