import React, { useState, useEffect } from "react";
import XcrackIcon from "../../resources/twitter/xcrack-logo.jpg";
import "./bodystyles.css";
import { useNavigate } from "react-router-dom";

const Body = () => {
  const Navigate = useNavigate();
  const [username, setUsername] = useState(""); // State to track email input value
  const [password, setPassword] = useState(""); // State to track email input value
  const [isOpen, setIsOpen] = useState(false);
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const [response, setResponse] = useState("");

  const handlePasswordChange = (e) => {
    setPassword(e.target.value); // Update email state on input change
  };
  const handleUsernameChange = (e) => {
    setUsername(e.target.value); // Update email state on input change
  };

  const SigninItem = {
    username: username,
    password: password,
  };

  useEffect(() => {
    const handleResize = () => {
      setWindowWidth(window.innerWidth);
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  async function fetchProfileInfo(username) {
    try {
      const response = await fetch(
        `http://localhost:8080/user/${encodeURIComponent(username)}/details`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      if (!response.ok) {
        throw new Error("Error: ${response.status}");
      }
      const profileData = await response.json();
      console.log(profileData);
      Navigate("/in/home/for-you", { state: { profileData } });
    } catch (error) {
      console.error("Error fetching profile info: ", error);
    }
  }
  const handleSigninButtonClick = async () => {
    try {
      const res1 = await fetch("http://localhost:8080/login/authenticate", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(SigninItem),
      });
      const data = await res1.text();
      if (data === "Login successful!") {
        fetchProfileInfo(SigninItem.username);
      } else {
        setResponse(data);
      }
    } catch (error) {
      setResponse("Failed to register. Please try again.");
      console.error("Error:", error);
    }
  };
  const handleCreateAccButtonClick = () => {
    Navigate("/verification/user/create-account");
  };
  const handleForgotPassButtonClick = () => {
    Navigate("/verification/user/forgot-password/fill");
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
    <div className="start-page-container">
      <div className="filler-1"></div>
      <div
        style={{
          display: windowWidth >= 900 ? "block" : "none",
          flex: windowWidth >= 900 ? "3" : "0",
        }}
      >
        <div className="start-page-logo-container">
          <img src={XcrackIcon} />
        </div>
      </div>
      <div className="start-page-body-container">
        <div className="filler-2">
          <img
            src={XcrackIcon}
            style={{ width: windowWidth <= 900 ? "65px" : "0px" }}
          />
        </div>
        <div className="start-page-body-container-child" style={{ flex: "7" }}>
          <p className="start-page-body-header-1">
            Happening {windowWidth < 900 ? <br /> : " "}now
          </p>
          <p className="start-page-body-header-2">Join today.</p>
          <div className="start-page-body-container-child-child">
            <div class="pass-user-container">
              <div class="field">
                <label for="username">Username</label>
                <input
                  type="text"
                  id="username"
                  name="username"
                  placeholder=" "
                  value={username}
                  onChange={handleUsernameChange}
                />
              </div>
              <div class="field">
                <label for="password">Password</label>
                <input
                  type="password"
                  id="password"
                  name="password"
                  placeholder=" "
                  value={password}
                  onChange={handlePasswordChange}
                />
              </div>
            </div>
            <button
              onClick={() => handleSigninButtonClick()}
              className="sign-in-account-button"
              style={{
                backgroundColor: password && username ? "" : "#ccc",
              }}
            >
              Sign in
            </button>
            <button
              className="forgot-pass-account-button"
              onClick={() => handleForgotPassButtonClick()}
            >
              Forgot password?
            </button>
            <div class="line-container">
              <span class="line"></span>
              <span class="text">or</span>
            </div>
            <button
              className="create-account-button"
              onClick={() => handleCreateAccButtonClick()}
            >
              Create account
            </button>
            <p className="terms-and-condition-1">
              By signing up, you agree to the{" "}
              <span>
                <a
                  target="_blank"
                  rel="noopener noreferrer"
                  href="https://x.com/en/tos"
                  class="terms-and-condition-2"
                >
                  Terms of Service
                </a>
              </span>{" "}
              and <span className="terms-and-condition-2">Privacy Policy,</span>{" "}
              including{" "}
              <span className="terms-and-condition-2">Cookie Use.</span>
            </p>
            <div
              style={{
                position: "absolute",
                bottom: isVisible ? "-80px" : "",
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
        </div>
        <div className="filler-2" />
      </div>
      <div className="filler-1"></div>
    </div>
  );
};
export default Body;
