import React, { useState, useEffect } from "react";
import XcrackLogo from "../../resources/twitter/xcrack-logo.jpg";
import { useNavigate, Routes, Route } from "react-router-dom";
import CreateAcc from "../createacc/createacc-1";
import ForgotPass from "../forgotpass/forgotpassword";
import ForgotPassConfirm from "../forgotpassconfirm/forgotpassconfirm";
import VerificationCode1 from "../verificationcode1/verificationcode1";
import VerificationCode2 from "../verificationcode2/verificationcode2";
import ChangePassword from "../changepass/changepass";
import InitialTagging from "../createacc/initialtagging";


const VerificationTemplate = () => {
  const Navigate = useNavigate();

  return (
    <div className="create-acc-container-1">
      <Routes>
        <Route path="create-account" element={<CreateAcc />} />
        <Route path="create-account/code" element={<VerificationCode2 />} />
        <Route path="create-account/tagging" element={<InitialTagging />} />
        <Route path="change-password" element={<ChangePassword />} />
        <Route path="forgot-password/fill" element={<ForgotPass />} />
        <Route path="forgot-password/confirmation" element={<ForgotPassConfirm />} />
        <Route path="forgot-password/confirmation/code" element={<VerificationCode1 />} />
      </Routes>
    </div>
  );
};
export default VerificationTemplate;
