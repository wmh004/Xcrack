import React, { useState, useEffect } from "react";
import XcrackLogo from "../../resources/twitter/xcrack-logo.jpg";
import { useNavigate } from "react-router-dom";

const VerificationCode2 = () => {
  const [code, setCode] = useState(""); // State to track email input value
  const Navigate = useNavigate();
  const [response, setResponse] = useState("");

  const handleItemClick = () => {
    Navigate("/verification/user/create-account");
  };
  const handleCodeChange = (e) => {
    setCode(e.target.value); // Update email state on input change
  };

  const VerificationItem = {
    verificationCode: code,
  };

  // Button click handler
  const handleNextButtonClick = async () => {
    try {
      const res = await fetch("http://localhost:8080/signUp/verify", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(VerificationItem),
      });
      const data = await res.text();
      if (data === "Registration successful!") {
        Navigate("/verification/login");
      } else {
        setResponse(data);
      }
    } catch (error) {
      setResponse("Failed to register. Please try again. e");
      console.error("Error:", error);
    }
  };

  const [isVisible, setIsVisible] = useState(false);
  useEffect(() => {
    if (response) {
      setIsVisible(true);
      setTimeout(() => {
        setIsVisible(false);setResponse("");
      }, 2000); // Delay and hide after 300ms
    }
  }, [response]);

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
          We sent you a code
        </p>
        <p
          className="text"
          style={{
            marginTop: "15px",
            marginBottom: "40px",
          }}
        >
          Check your email to get your confirmation code. If you need to request
          a new code, go back and reselect a confirmation.
        </p>
        <div class="field">
          <input
            type="text"
            id="email-forgot-pass"
            name="email"
            placeholder=" "
            value={code}
            onChange={handleCodeChange} // Handle input change
          />
          <label for="email">Enter your code</label>
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
            code ? "active" : ""
          }`} /* Conditional class based on email state*/
        >
          Next
        </button>
      </div>
      <div
        style={{
          position: "fixed",
          bottom: isVisible ? "40px" : "",
          backgroundColor: "rgb(29, 155, 240)",
          padding: "10px 20px",
          borderRadius: "10px",
          transition: "bottom 0.3s ease-in-out",
          display: isVisible ? "" : "none",
        }}
      >
        {response}
      </div>
    </div>
  );
};
export default VerificationCode2;
