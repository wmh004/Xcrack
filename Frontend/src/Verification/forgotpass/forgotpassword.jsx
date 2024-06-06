import React, { useState, useEffect } from "react";
import "./forgotpasswordstyles.css";
import XcrackLogo from "../../resources/twitter/xcrack-logo.jpg";
import { useNavigate } from "react-router-dom";

const ForgotPass = () => {
  const [email, setEmail] = useState(""); // State to track email input value
  const Navigate = useNavigate();

  const handleItemClick = () => {
    Navigate("/verification/login");
  };

  // Button click handler
  const handleNextButtonClick = () => {
    if (email) {
      Navigate("/verification/user/forgot-password/confirmation", {
        state: { email }, //send the email to forgotpass confirmation page
      });
    }
  };

  const handleEmailChange = (e) => {
    setEmail(e.target.value); // Update email state on input change
  };

  return (
    <div className="create-acc-container-2">
      <div className="filler-3">
        <img className="create-acc-logo" src={XcrackLogo} />
        <div
          onClick={() => handleItemClick()}
          className="create-acc-close-button"
        >
          X
        </div>
      </div>
      <div
        className="create-acc-contents"
        style={{
          boxSizing: "border-box",
          padding: "5px 20px",
        }}
      >
        <p
          className="header-1"
          style={{
            marginTop: "15px",
            marginBottom: "10px",
          }}
        >
          Find your Xcrack account
        </p>
        <p
          className="text"
          style={{
            marginTop: "15px",
            marginBottom: "40px",
          }}
        >
          Enter the email associated with your account to change your password.
        </p>
        <div class="field">
          <input
            type="email"
            id="email-forgot-pass"
            name="email"
            placeholder=" "
            value={email}
            onChange={handleEmailChange} // Handle input change
          />
          <label for="email">Email</label>
        </div>
      </div>
      <div
        className="filler-3"
        style={{
          boxSizing: "border-box",
          padding: "5px 20px",
        }}
      >
        <button
          onClick={() => handleNextButtonClick()}
          className={`forgot-pass-next-button ${
            email ? "active" : ""
          }`} /* Conditional class based on email state*/
        >
          Next
        </button>
      </div>
    </div>
  );
};
export default ForgotPass;
