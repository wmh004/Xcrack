import React, { useEffect , useState} from "react";
import { Link, Route, Routes, useLocation } from "react-router-dom";
import ProfileHeader from "./profileheader/profileheader";
import ProfilePic from "../../resources/twitter/me.jpg";
import ProfileBackgroundPic from "../../resources/twitter/shelbymustanggt500-pic.jpeg";
import ProfileIcon from "../../resources/icons/profile-icon.png";
import ProfileSummary from "./profilesummary/profilesummary";
import Posts from "./posts/posts.jsx";
import Replies from "./replies/replies.jsx";
import Highlights from "./highlights/highlights.jsx";
import Media from "./media/media.jsx";
import Likes from "./likes/likes.jsx";
import NavBar from "./navbar/navbar.jsx";
import { useProfile } from "../../DataSets/ProfileContext.js";

const Profile = () => {
  const { profileData, setProfileData } = useProfile();
  const { profiletoken, setProfileToken } = useProfile();
  const location = useLocation();
  const [isLoading, setIsLoading] = useState(true); // State to track loading status

  useEffect(() => {
    if (location.state && location.state.profileData) {
      setProfileData(location.state.profileData);
    }
  }, [location.state, setProfileData]);

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const options = { year: "numeric", month: "long" };
    return date.toLocaleDateString(undefined, options);
  };

  const UserItem = [
    {
      name: profileData ? profileData.name : "MUHAMMAD HAKIMI BIN MAHADZIR",
      username: profileData ? "@" + profileData.username : "@MUHAMMA44469768",
      bio: profileData ? profileData.bio : "Your average SE guy",
      time: "15h",
      join_date: formatDate(profileData.dob),
      following: "0",
      followers: "0",
      profile_pic: (
        <img
          style={{ height: "150px", width: "auto" }}
          src={ProfilePic}
          alt={ProfileIcon}
        />
      ),
      background_pic: (
        <img
          style={{ height: "100%", width: "100%", objectFit: "cover" }}
          src={ProfileBackgroundPic}
          alt={<div style={{ backgroundColor: "white" }} />}
        />
      ),
    },
  ];

  return (
    <div>
      <ProfileHeader item={UserItem} />
      <ProfileSummary item={UserItem} />
      <NavBar />
      <div>
        <Routes>
          <Route path="/" element={<Posts />} />
          <Route path="posts" element={<Posts />} />
          <Route path="replies" element={<Replies />} />
          <Route path="highlights" element={<Highlights />} />
          <Route path="media" element={<Media />} />
          <Route path="likes" element={<Likes />} />
        </Routes>
      </div>
    </div>
  );
};
export default Profile;
