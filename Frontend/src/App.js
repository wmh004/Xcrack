import React from "react";
import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import StartPage from "./Verification/startpage.jsx";
import PagesNav from "./Pages/pagesnav.jsx";
import CreateAcc from "./Verification/createacc/createacc-1.jsx";
import ForgotPass from "./Verification/forgotpass/forgotpassword.jsx";
import VerificationTemplate from "./Verification/verificationtemplate/verificationtemplate.jsx";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/*" element={<StartPage />} />
        <Route path="/verification/*" element={<StartPage />} />
        <Route path="/in/*" element={<PagesNav />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
