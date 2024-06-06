import React from "react";
import "./navbarstyles.css";
import { NavLink } from "react-router-dom";

const NavBar = () => {
  const navItem = [
    {
      path: "/in/profile/posts",
      name: "Posts",
    },
    {
      path: "/in/profile/replies",
      name: "Replies",
    },
    {
      path: "/in/profile/highlights",
      name: "Highlights",
    },
    {
      path: "/in/profile/media",
      name: "Media",
    },
    {
      path: "/in/profile/likes",
      name: "Likes",
    },
  ];

  return (
    <div className="profile-nav-bar-container">
      {navItem.map((item, index) => (
        <NavLink
          to={item.path}
          key={index}
          className="profile-nav-bar-container-child"
          end={item.path === "/"}
        >
          <p>{item.name}</p>
        </NavLink>
      ))}
    </div>
  );
};
export default NavBar;
