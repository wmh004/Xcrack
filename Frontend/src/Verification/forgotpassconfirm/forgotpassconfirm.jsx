import React, { useState, useEffect } from "react";
import XcrackLogo from "../../resources/twitter/xcrack-logo.jpg";
import { useNavigate, useLocation } from "react-router-dom";

const ForgotPassConfirm = () => {
  const Location = useLocation();
  const email = Location.state?.email; //get the email from forgotpass page
  const [response, setResponse] = useState("");
  // Function to format the email
  const formatEmail = (email) => {
    const [username, domain] = email.split("@");
    const usernameFormatted =
      username.slice(0, 2) +
      "*".repeat(username.length - 4) +
      username.slice(-2);
    const domainParts = domain.split(".");
    const domainFormatted =
      domainParts[0][0] + "*".repeat(domainParts[0].length - 1);
    const domainExtension = "*".repeat(domainParts[1].length);
    return `${usernameFormatted}@${domainFormatted}.${domainExtension}`;
  };

  const formattedEmail = formatEmail(email);

  const EmailItem = {
    email: email,
  };

  const Navigate = useNavigate();
  const handleItemClick = () => {
    Navigate("/verification/user/forgot-password/fill");
  };
  const handleNextButtonClick = async () => {
    try {
      const res = await fetch(
        "http://localhost:8080/forgotPassword/forgot-password",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(EmailItem),
        }
      );
      const data = await res.text();
      if (data === "Password reset email sent. Please check your email.") {
        Navigate("/verification/user/forgot-password/confirmation/code", {
          state: { email }, //send the email to verification page
        });
      } else {
        setResponse(data);
      }
    } catch (error) {
      setResponse("Failed to register. Please try again.");
      console.error("Error:", error);
    }
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
          display: "flex",
          flexDirection: "column",
        }}
      >
        <div>
          <p
            className="header-1"
            style={{
              marginTop: "15px",
              marginBottom: "10px",
            }}
          >
            Where should we send a confirmation code?
          </p>
          <p
            className="text"
            style={{
              fontSize: "17px",
              marginTop: "15px",
              marginBottom: "40px",
            }}
          >
            Before you can change your password, we need to make sure it’s
            really you. <br />
            <br />
            Start by choosing where to send a confirmation code.
          </p>
          <p
            style={{
              fontSize: "17px",
              fontWeight: "550",
              marginTop: "15px",
              marginBottom: "10px",
            }}
          >
            Send an email to {formattedEmail}
          </p>
          <p
            style={{
              fontSize: "17px",
              marginTop: "40px",
              marginBottom: "10px",
            }}
          >
            Contact{" "}
            <span
              style={{
                fontSize: "17px",
                color: "rgb(29, 155, 240)",
              }}
            >
              Xcrack Support
            </span>{" "}
            if you don't have access.
          </p>
        </div>
        <div
          style={{
            flex: "1",
            display: "flex",
            alignItems: "flex-end",
          }}
        >
          <button
            className="sign-in-account-button"
            style={{
              padding: "16px",
              margin: "0px",
            }}
            onClick={() => handleNextButtonClick()}
          >
            Next
          </button>
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
          className="forgot-pass-account-button"
          style={{
            padding: "16px",
          }}
          onClick={() => handleItemClick()}
        >
          Cancel
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
export default ForgotPassConfirm;
