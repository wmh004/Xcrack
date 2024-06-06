import React, { useState, useEffect } from "react";
import "./Sidebarstyles.css";
import { NavLink, useNavigate, useLocation } from "react-router-dom";
import HomeIcon from "../../resources/icons/home-icon.png";
import ExploreIcon from "../../resources/icons/explore-icon.png";
import NotificationsIcon from "../../resources/icons/notifications-icon.png";
import MessagesIcon from "../../resources/icons/messages-icon.png";
import ProfileIcon from "../../resources/icons/profile-icon.png";
import MoreIcon from "../../resources/icons/more-icon-v3.png";
import PostIcon from "../../resources/icons/feather-icon.png";
import Xcrack from "../../resources/twitter/xcrack-logo.jpg";
import { useProfile } from "../../DataSets/ProfileContext";

const Sidebar = () => {
  const Navigate = useNavigate();
  const [isHovered1, setIsHovered1] = useState(false);
  const { profileData, setProfileData } = useProfile();
  const location = useLocation();

  useEffect(() => {
    if (location.state && location.state.profileData) {
      setProfileData(location.state.profileData);
    }
  }, [location.state, setProfileData]);

  const [isOpen, setIsOpen] = useState(false);
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);

  useEffect(() => {
    const handleResize = () => {
      setWindowWidth(window.innerWidth);
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  const toggle = () => setIsOpen(!isOpen);

  const handleItemClick = () => {
    Navigate("/verification/login");
  };

  const menuItem = [
    {
      path: "/in/home/for-you",
      name: "Home",
      icon: (
        <img
          style={{ height: "35px", width: "35px" }}
          className="sidebar-icon"
          src={HomeIcon}
          alt="Home"
        />
      ),
    },
    {
      path: "/in/explore",
      name: "Explore",
      icon: (
        <img
          style={{ height: "35px", width: "35px" }}
          className="sidebar-icon"
          src={ExploreIcon}
          alt="Explore"
        />
      ),
    },
    {
      path: "/in/notifications",
      name: "Notifications",
      icon: (
        <img
          className="sidebar-icon"
          style={{ height: "35px", width: "35px" }}
          src={NotificationsIcon}
          alt="Notifications"
        />
      ),
    },
    {
      path: "/in/message",
      name: "Messages",
      icon: (
        <img
          style={{ height: "35px", width: "35px" }}
          className="sidebar-icon"
          src={MessagesIcon}
          alt="Messages"
        />
      ),
    },
    {
      path: "/in/profile/posts",
      name: "Profile",
      icon: (
        <img
          style={{ height: "35px", width: "35px" }}
          className="sidebar-icon"
          src={ProfileIcon}
          alt="Profile"
        />
      ),
    },
    {
      path: "/in/more",
      name: "More",
      icon: (
        <img
          style={{ height: "35px", width: "35px" }}
          className="sidebar-icon"
          src={MoreIcon}
          alt="More"
        />
      ),
    },
    {
      path: "/in/post",
      name: "Post",
      icon: (
        <img
          style={{ height: "35px", width: "35px" }}
          className="sidebar-icon"
          src={PostIcon}
          alt="More"
        />
      ),
    },
  ];

  return (
    <sidebar className="sidebar-container">
      <div
        style={{
          position: "sticky",
          top: "0",
          right: "0",
          left: "0",
          bottom: "0",
        }}
      >
        <section className="sidebar">
          <div
            className="bars"
            style={{
              justifyContent: windowWidth >= 1620 ? "flex-start" : "center", // Horizontally center if less than 1620px, else left align
              paddingRight: windowWidth >= 1620 ? "0px" : "15px",
            }}
          >
            <img
              style={{ height: "50px", width: "60px", border: "none" }}
              className="sidebar-icon"
              src={Xcrack}
              alt="More"
              onClick={toggle}
            />
          </div>

          {menuItem.map((item, index) => (
            <NavLink to={item.path} key={index} className="link">
              <div
                style={{
                  backgroundColor:
                    item.name === "Post" ? "rgb(29, 155, 240)" : "",
                }}
                className="link-child"
              >
                <div
                  style={{
                    padding: item.name === "Post" ? "13px" : "10px",
                  }}
                  className="icon"
                >
                  {item.icon}
                </div>
                <div
                  className="link_text"
                  style={{
                    display: windowWidth >= 1620 ? "block" : "none",
                    width: windowWidth >= 1620 ? "280px" : "auto",
                  }}
                >
                  {item.name}
                </div>
              </div>
            </NavLink>
          ))}
          <div
            className="bars-bottom"
            style={{
              justifyContent: windowWidth >= 1620 ? "flex-start" : "center", // Horizontally center if less than 1620px, else left align
              paddingRight: windowWidth >= 1620 ? "0px" : "15px",
            }}
          >
            <div
              onMouseEnter={() => setIsHovered1(true)}
              onMouseLeave={() => setIsHovered1(false)}
              style={{
                position: "relative",
                display: "flex",
                flexDirection: "row",
                justifyContent: "center",
                alignItems: "center",
                width: "100%",
              }}
            >
              <div
                className={`tooltip ${isHovered1 ? "show-tooltip" : ""}`}
                onClick={() => handleItemClick()}
              >
                <p>Log out</p>
                <p>@{profileData.username}</p>
              </div>
              <img
                style={{ height: "50px", width: "60px", border: "none" }}
                src={Xcrack}
                alt="User"
                onClick={toggle}
              />
              <div
                style={{
                  flex: "3",
                  display: windowWidth >= 1620 ? "flex" : "none",
                  width: windowWidth >= 1620 ? "280px" : "auto",
                  flexDirection: "column",
                  justifyContent: "center",
                  alignItems: "flex-start",
                  paddingLeft: "5px",
                  boxSizing:
                    "border-box" /* Ensure padding is included in the width and height */,
                }}
              >
                <div
                  style={{
                    fontWeight: "550px",
                  }}
                >
                  {profileData ? <p>{profileData.name}</p> : <p>name</p>}
                  {/* <p>{profileData.name}</p> */}
                </div>
                <div>
                  {profileData ? (
                    <p
                      style={{
                        color: "rgb(113, 118, 123)",
                      }}
                    >
                      @{profileData.username}
                    </p>
                  ) : (
                    <p>username</p>
                  )}
                  {/* <p>{profileData.username}</p> */}
                </div>
              </div>
              <div
                style={{
                  height: windowWidth >= 1620 ? "20px" : "auto",
                  display: windowWidth >= 1620 ? "flex" : "none",
                  width: windowWidth >= 1620 ? "40px" : "auto",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                <img
                  style={{
                    height: windowWidth >= 1620 ? "20px" : "auto",
                    display: windowWidth >= 1620 ? "flex" : "none",
                    width: windowWidth >= 1620 ? "20px" : "auto",
                  }}
                  className="sidebar-icon"
                  src={MoreIcon}
                  alt="More"
                />
              </div>
            </div>
          </div>
        </section>
      </div>
    </sidebar>
  );
};
export default Sidebar;
