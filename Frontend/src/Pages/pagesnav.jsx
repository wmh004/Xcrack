import React from "react";
import { Route, Routes } from "react-router-dom";
import Sidebar from "../Components/Sidebar/Sidebar.jsx";
import Info from "../Components/Info/Info.jsx";
import Home from "../Pages/home/home.jsx";
import Explore from "../Pages/explore/explore.jsx";
import Message from "../Pages/message/message.jsx";
import Notification from "../Pages/notifications/notifications.jsx";
import Profile from "../Pages/profile/profile.jsx";
import More from "../Pages/more/more.jsx";
import Reply from "../Pages/home/reply/reply.jsx";
import { ProfileProvider } from "../DataSets/ProfileContext.js";

const PagesNav = () => {
  return (
    <ProfileProvider>
      <div className="app-container">
        <div className="filler" />
        <Sidebar />
        <div className="content">
          <Routes>
            <Route path="/home/*" element={<Home />} />
            <Route path="/reply" element={<Reply />} />
            <Route path="/explore" element={<Explore />} />
            <Route path="/message" element={<Message />} />
            <Route path="/notifications" element={<Notification />} />
            <Route path="/profile/*" element={<Profile />} />
            <Route path="/more" element={<More />} />
          </Routes>
        </div>
        <Info />
        <div className="filler" />
      </div>
    </ProfileProvider>
  );
};
export default PagesNav;
