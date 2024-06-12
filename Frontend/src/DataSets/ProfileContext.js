import React, { createContext, useContext, useState, useEffect } from "react";

const ProfileContext = createContext();

export const useProfile = () => useContext(ProfileContext);

export const ProfileProvider = ({ children }) => {
  const [profileData, setProfileData] = useState(() => {
    const savedProfile = localStorage.getItem("profileData");
    return savedProfile ? JSON.parse(savedProfile) : null;
  });

  const [profiletoken, setProfileToken] = useState(() => {
    const token = localStorage.getItem("profileDataUser");
    return token ? JSON.parse(token) : null;
  });

  useEffect(() => {
    if (profileData) {
      localStorage.setItem("profileData", JSON.stringify(profileData));
    }
  }, [profileData]);

  // useEffect(() => {
  //   if (profileDataUser) {
  //     localStorage.setItem("profileData", JSON.stringify(profileDataUser));
  //   }
  // }, [profileDataUser]);


  return (
    <ProfileContext.Provider value={{ profileData, setProfileData, profiletoken, setProfileToken }}>
      {children}
    </ProfileContext.Provider>
  );
};
