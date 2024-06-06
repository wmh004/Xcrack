import React from "react";
import { useNavigate, useLocation } from "react-router-dom";

const Following = () => {
  const Location = useLocation();
  const { profileData } = Location.state || {};

  return (
    <div>
      <p>Following</p>
    </div>
  );
};
export default Following;
