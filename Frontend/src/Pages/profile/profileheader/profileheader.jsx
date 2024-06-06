import React, { useEffect } from "react";
import ArrowIcon from "../../../resources/icons/arrow-left-icon.png";
import "../profilestyles.css";
import { useNavigate, useLocation } from "react-router-dom";
import { useProfile } from "../../../DataSets/ProfileContext";

const ProfileHeader = ({ item }) => {
  const Navigate = useNavigate();

  const handleItemClick = () => {
    Navigate("/in/home/for-you");
  };

  const { profileData, setProfileData } = useProfile();
  const location = useLocation();

  useEffect(() => {
    if (location.state && location.state.profileData) {
      setProfileData(location.state.profileData);
    }
  }, [location.state, setProfileData]);

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
          {profileData.name}
        </div>
      </header>
    </div>
  );
};

export default ProfileHeader;
