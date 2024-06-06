import React from "react";
import ArrowIcon from "../../../resources/icons/arrow-left-icon.png";
import "./replystyles.css";
import { useNavigate } from "react-router-dom";

const Replyheader = () => {
  const Navigate = useNavigate();

  const handleItemClick = () => {
    Navigate("/in/home/for-you");
  };

  return (
    <div
      style={{
        position: "sticky",
        top: "0",
        right: "0",
        left: "0",
        height: "70px",
        zIndex:"100"
      }}
    >
      <header className="reply-header-container">
        <div className="center">
          <div
            className="reply-header-arrow center"
            onClick={() => handleItemClick()}
          >
            <img
              src={ArrowIcon}
              style={{ height: "23px", width: "23px" }}
              alt="Back"
            />
          </div>
        </div>
        <div
          className="center"
          style={{
            justifyContent: "flex-start",
          }}
        >
          Post
        </div>
      </header>
    </div>
  );
};

export default Replyheader;
