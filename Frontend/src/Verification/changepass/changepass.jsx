import React, { useState, useEffect } from "react";
import XcrackLogo from "../../resources/twitter/xcrack-logo.jpg";
import { useNavigate } from "react-router-dom";

const ChangePassword = () => {
  const [response, setResponse] = useState("");
  const [newpass, setNewPass] = useState(""); // State to track email input value
  const [confirmnewpass, setConfirmNewPass] = useState(""); // State to track email input value
  const Navigate = useNavigate();

  const handleItemClick = () => {
    Navigate("/verification/login");
  };

  // Button click handler
  const handleNextButtonClick = async () => {
    if (!newpass || !confirmnewpass) {
      setResponse("Please enter new password");
    } else{ 
      if (newpass === confirmnewpass) {
        const PassItem = {
          newPassword: newpass,
        };
        try {
          const res = await fetch(
            "http://localhost:8080/forgotPassword/reset-password",
            {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify(PassItem),
            }
          );
          const data = await res.text();
          if (data === "Password reset successful!") {
            Navigate("/verification/login");
            setResponse(data);
          } else {
            setResponse(data);
          }
        } catch (error) {
          setResponse("Failed to register. Please try again.");
          console.error("Error:", error);
        }
      } else {
        setResponse("Please match your passwords.");
      }
    }
  };
  const handleNewPassChange = (e) => {
    setNewPass(e.target.value); // Update email state on input change
  };
  const handleConfirmNewPassChange = (e) => {
    setConfirmNewPass(e.target.value); // Update email state on input change
  };

  const [isVisible, setIsVisible] = useState(false);
  useEffect(() => {
    if (response) {
      setIsVisible(true);
      setTimeout(() => {
        setIsVisible(false);
        setResponse("");
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
          Change Your Password
        </p>
        <p
          className="text"
          style={{
            marginTop: "15px",
            marginBottom: "40px",
          }}
        >
          You'll be logged out of all active Xcrack sessions after your password
          is changed.
        </p>
        <div class="field">
          <input
            type="password"
            id="email-forgot-pass"
            name="newpassword"
            placeholder=" "
            value={newpass}
            onChange={handleNewPassChange} // Handle input change
          />
          <label for="email">Enter a new password</label>
        </div>
        <div class="field" style={{ marginTop: "30px" }}>
          <input
            type="password"
            id="email-forgot-pass"
            name="password"
            placeholder=" "
            value={confirmnewpass}
            onChange={handleConfirmNewPassChange} // Handle input change
          />
          <label for="email">Confirm your password</label>
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
            newpass && confirmnewpass ? "active" : ""
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
export default ChangePassword;
