import React, { createContext, useContext, useState, useEffect } from 'react';

const ProfileContext = createContext();

export const useProfile = () => useContext(ProfileContext);

export const ProfileProvider = ({ children }) => {
    const [profileData, setProfileData] = useState(() => {
        const savedProfile = localStorage.getItem('profileData');
        return savedProfile ? JSON.parse(savedProfile) : null;
    });

    useEffect(() => {
        if (profileData) {
            localStorage.setItem('profileData', JSON.stringify(profileData));
        }
    }, [profileData]);

    return (
        <ProfileContext.Provider value={{ profileData, setProfileData }}>
            {children}
        </ProfileContext.Provider>
    );
};
