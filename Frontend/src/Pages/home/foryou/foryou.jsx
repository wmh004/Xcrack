import React, { useEffect, useState } from "react";
import "../homestyles.css";
import Tweet from "../../../Components/tweet/tweet";
import { useNavigate, useLocation } from "react-router-dom";
import { useProfile } from "../../../DataSets/ProfileContext";

const Foryou = () => {
  const location = useLocation();
  const [selectedFiles, setSelectedFiles] = useState([]);
  const { profileData, setProfileData } = useProfile();
  useEffect(() => {
    if (location.state && location.state.profileData) {
      setProfileData(location.state.profileData);
    }
  }, [location.state, setProfileData]);
  const Navigate = useNavigate();

  const [toggledIndex, setToggledIndex] = useState(null);

  const handleToggleMore = (index, event) => {
    event.stopPropagation();
    setToggledIndex(toggledIndex === index ? null : index);
  };
  const handleToggleTooltip = (event) => {
    event.stopPropagation();
    setToggledIndex(null);
  };

  const handleItemClick = (item) => {
    Navigate("/in/reply", { state: { item } }); /*pass item to tweet */
  };

  const [posts, setPosts] = useState([]);

  const transformData = (apiData) => {
    return apiData.map((item) => {
      return {
        username: item.name,
        account_name: item.username, // Assuming "name" from API corresponds to "account_name" in your frontend
        time: item.timeCreated, // You may need to format this according to your frontend's requirements
        datecreated: item.dateCreated,
        captions: item.content,
        comment_count: item.replyCount,
        rt_count: item.repostCount,
        like_count: item.likeCount,
        view_count: item.viewCount,
        save_count: item.bookmarkCount, // Assuming "bookmarkCount" from API corresponds to "save_count" in your frontend
        media: selectedFiles, // You'll need to fill this based on your selected files
        id: item.id,
      };
    });
  };

  const fetchPosts = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/recommend/posts?username=${encodeURIComponent(profileData.username)}`
      );
      const data = await response.json();
      const transformedData = transformData(data);
      setPosts(transformedData);
    } catch (error) {
      console.error("Error fetching posts:", error);
    }
  };

  useEffect(() => {
    fetchPosts();
  }, []); // Empty dependency array means it will only run once, similar to componentDidMount

  useEffect(() => {
    console.log("Posts after update:", posts);
  }, [posts]); // Empty dependency array means it will only run once, similar to componentDidMount

  return (
    <div className="home-contents-container">
      {posts.map((item, index) => (
        <div
          style={{
            width: "100%",
            position: "relative",
          }}
          key={index}
          onClick={() => handleItemClick(item)}
        >
          {/*pass item to tweet */}
          <Tweet
            item={item}
            onMoreButtonClick={(event) => handleToggleMore(index, event)}
          />{" "}
          {toggledIndex === index && (
            <div
              className="more-tooltip"
              onClick={(event) => handleToggleTooltip(event)}
            >
              <p
                style={{
                  padding: "7px 20px",
                  color: "white",
                  borderRadius: "40px",
                  fontWeight: "550",
                }}
              >
                Follow @{item.account_name}
              </p>
            </div>
          )}
        </div>
      ))}
    </div>
  );
};
export default Foryou;
