import React from "react";
import "../homestyles.css";
import { NavLink } from "react-router-dom";
import SettingIcon from "../../../resources/icons/setting-icon.png";

const Homeheader = () => {
  const navItem = [
    {
      path: "/in/home/for-you",
      name: "For you",
    },
    {
      path: "/in/home/following",
      name: "Following",
    },
  ];

  return (
    <div
      style={{
        position: "sticky",
        top: "0",
        right: "0",
        left: "0",
        height: "70px",
        zIndex: "100",
      }}
    >
      <header className="home-header-container">
        {navItem.map((item, index) => (
          <NavLink
            to={item.path}
            key={index}
            className="home-header-container-child"
            end={item.path === "/"}
          >
            <p>{item.name}</p>
          </NavLink>
        ))}
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <img
            style={{ height: "25px", width: "25px" }}
            src={SettingIcon}
            alt="Timeline settings"
          />
        </div>
      </header>
    </div>
  );
};

export default Homeheader;
