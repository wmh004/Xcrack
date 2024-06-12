import React, { useState, useEffect } from "react";
import "./createaccstyles.css";
import XcrackLogo from "../../resources/twitter/xcrack-logo.jpg";
import { useNavigate } from "react-router-dom";

const CreateAcc = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const Navigate = useNavigate();
  const [isDetailsFilled, setIsDetailsFilled] = useState(false);
  const [name, setName] = useState("");
  const [username, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [month, setMonth] = useState("");
  const [day, setDay] = useState("");
  const [year, setYear] = useState("");
  const [password, setPassword] = useState("");
  const [days, setDays] = useState([]);
  const [years, setYears] = useState([]);

  const handleNameChange = (e) => {
    setName(e.target.value); // Update email state on input change
  };
  const handleUserNameChange = (e) => {
    setUserName(e.target.value); // Update email state on input change
  };
  const handleEmailChange = (e) => {
    setEmail(e.target.value); // Update email state on input change
  };
  const handlePasswordChange = (e) => {
    setPassword(e.target.value); // Update email state on input change
  };

  const [response, setResponse] = useState("");

  const VerificationItem = {
    name: name,
    dob: formatDate(year, month, day),
    email: email,
    username: username,
    password: password,
  };

  function formatDate(year, month, day) {
    // Convert month to zero-based index
    const monthIndex =
      [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
      ].indexOf(month) + 1;

    // Ensure month and day are two digits
    const formattedMonth = monthIndex < 10 ? "0" + monthIndex : monthIndex;

    // Combine into desired format
    const formattedDate = `${year}-${formattedMonth}-${day}`;

    return formattedDate;
  }

  useEffect(() => {
    // Check if all fields are filled
    if (name && username && email && password && month && day && year) {
      setIsDetailsFilled(true);
    } else {
      setIsDetailsFilled(false);
    }
  }, [name, email, password, month, day, year, username]);
  const handleItemClick = () => {
    Navigate("/verification/login", {
      state: {username },
    });
  };

  // Button click handler
  const handleNextButtonClick = async () => {
    if (isDetailsFilled) {
      // All fields are filled, navigate to the next page
      try {
        const res = await fetch("http://localhost:8080/signUp/add", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(VerificationItem),
        });
        const data = await res.text();
        if (
          data ===
          "Verification code sent to your email. Please check and verify."
        ) {
          setResponse(data);
          Navigate("/verification/user/create-account/code", {
            state: {username}
          });
        } else {
          setResponse(data);
        }
      } catch (error) {
        setResponse("Failed to register. Please try again. e");
        console.error("Error:", error);
      }
    }
  };
  // CSS class for the button
  const buttonClass = isDetailsFilled
    ? "create-acc-next-button filled"
    : "create-acc-next-button";

  useEffect(() => {
    const handleResize = () => {
      setWindowWidth(window.innerWidth);
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  useEffect(() => {
    // Populate days from 1 to 31
    const daysArray = Array.from({ length: 31 }, (_, i) => i + 1);
    setDays(daysArray);

    // Calculate the current year minus 15 for the upper limit
    const currentYear = new Date().getFullYear();
    const maxYear = currentYear - 13;
    // Populate years from 1900 to maxYear
    const yearsArray = Array.from(
      { length: maxYear - 1904 + 1 },
      (_, i) => 1904 + i
    );
    setYears(yearsArray);
  }, []);

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
        <p className="header-1">Create your account</p>
        <div
          class="create-acc-container-2-child-1"
          style={{
            height: "300px",
            gap: "20px",
            marginBottom: "70px",
            gridTemplateRows: "1fr 1fr 1fr 1fr",
          }}
        >
          <div class="field">
            <input
              type="text"
              id="username"
              name="username"
              placeholder=" "
              value={name}
              onChange={handleNameChange}
            />
            <label for="username">Full Name</label>
          </div>
          <div class="field">
            <input
              type="email"
              id="email"
              name="email"
              placeholder=" "
              value={email}
              onChange={handleEmailChange}
            />
            <label for="password">Email</label>
          </div>
          <div class="field">
            <input
              type="text"
              id="username"
              name="username"
              placeholder=" "
              value={username}
              onChange={handleUserNameChange}
            />
            <label for="username">Username</label>
          </div>
          <div class="field">
            <input
              type="password"
              id="password"
              name="password"
              placeholder=" "
              value={password}
              onChange={handlePasswordChange}
            />
            <label for="password">Password</label>
            <label
              for="password"
              style={{
                top: "70px",
                fontSize: "12px",
              }}
            >
              8 characters long, contain at least one digit, one uppercase
              letter, one lowercase letter, and one special character.
            </label>
          </div>
        </div>

        <p className="header-2">Date of birth</p>
        <p className="text">
          This will not be shown publicly. Confirm your own age, even if this
          account is for a business, a pet, or something else.
        </p>
        <div class="create-acc-container-2-child-2">
          <div class="field">
            <select
              id="month"
              name="month"
              value={month}
              onChange={(e) => setMonth(e.target.value)}
            >
              <option value="" disabled hidden></option>
              <option value="January">January</option>
              <option value="February">February</option>
              <option value="March">March</option>
              <option value="April">April</option>
              <option value="May">May</option>
              <option value="June">June</option>
              <option value="July">July</option>
              <option value="August">August</option>
              <option value="September">September</option>
              <option value="October">October</option>
              <option value="November">November</option>
              <option value="December">December</option>
            </select>
            <label for="month">Month</label>
          </div>
          <div class="field">
            <select
              id="day"
              name="day"
              value={day}
              onChange={(e) => setDay(e.target.value)}
            >
              <option value="" disabled hidden></option>
              {days.map((d) => (
                <option key={d} value={d}>
                  {d}
                </option>
              ))}
            </select>
            <label for="day">Day</label>
          </div>
          <div class="field">
            <select
              id="year"
              name="year"
              value={year}
              onChange={(e) => setYear(e.target.value)}
            >
              <option value="" disabled hidden></option>
              {years.map((y) => (
                <option key={y} value={y}>
                  {y}
                </option>
              ))}
            </select>
            <label for="year">Year</label>
          </div>
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
          className={buttonClass}
          onClick={handleNextButtonClick}
          style={{
            backgroundColor: isDetailsFilled ? "rgb(239, 243, 244)" : "",
          }}
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
export default CreateAcc;
