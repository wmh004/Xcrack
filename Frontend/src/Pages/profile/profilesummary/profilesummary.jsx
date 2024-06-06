import React from "react";
import "./profilesummarystyles.css";
import { useProfile } from "../../../DataSets/ProfileContext";

const ProfileSummary = ({ item }) => {
  const user = item[0]; // Assuming there is always one user for simplicity

  return (
    <div className="container">
      <div className="picContainer">
        <div className="picContainer-top">
          {user.background_pic}
          <div className="profilePicContainer">{user.profile_pic}</div>
        </div>
        <div className="picContainer-bottom">
          <div className="button-setup-profile">Set up profile</div>
        </div>
      </div>
      <div className="infoContainer">
        <h2 className="name">{user.name}</h2>
        <p className="username">{user.username}</p>
        <p className="username" style={{ color: "white", marginTop: "10px" }}>
          {user.bio}
        </p>
        <p className="joinDate">Joined {user.join_date}</p>
        <p className="following">
          <strong>{user.following}</strong>{" "}
          <span style={{ color: "rgb(113, 118, 123)" }}>Following</span>
        </p>
        <p className="followers">
          <strong>{user.followers}</strong>{" "}
          <span style={{ color: "rgb(113, 118, 123)" }}>Followers</span>
        </p>
      </div>
    </div>
  );
};

export default ProfileSummary;
