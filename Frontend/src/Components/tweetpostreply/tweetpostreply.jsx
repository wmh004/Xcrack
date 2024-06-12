import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import "../../Pages/home/homestyles.css";
import GifIcon from "../../resources/icons/gif-icon.png";
import EmojiIcon from "../../resources/icons/emoji-icon.png";
import LocationIcon from "../../resources/icons/location-icon.png";
import ImageHandler from "../Imagehandler/Imagehandler";
import ImageGridDisplay from "../ImageGridDisplayChoose/ImageGridDisplayChoose";
import { useProfile } from "../../DataSets/ProfileContext";
import Tweet from "../tweet/tweet";

const Tweetpostreply = ({ item }) => {
  const [response, setResponse] = useState("");
  const { profileData, setProfileData } = useProfile();
  const [caption, setCaption] = useState("");
  const [selectedFiles, setSelectedFiles] = useState([]);
  const [posts, setPosts] = useState([]); // State to manage posts
  const location = useLocation();

  useEffect(() => {
    if (location.state && location.state.profileData) {
      setProfileData(location.state.profileData);
    }
  }, [location.state, setProfileData]);

  const handleCaptionChange = (e) => {
    setCaption(e.target.value);
  };
  const handleFileChange = (newFiles) => {
    setSelectedFiles((prevFiles) => [...prevFiles, ...newFiles]);
  };

  const handleRemoveFile = (indexToRemove) => {
    setSelectedFiles((prevFiles) =>
      prevFiles.filter((_, index) => index !== indexToRemove)
    );
  };

  const handleInput = (e) => {
    e.target.style.height = "30px";
    e.target.style.height = `${e.target.scrollHeight}px`;
  };

  const formatTime = (timeString) => {
    const date = new Date(`1970-01-01T${timeString}Z`);
    const hours = date.getUTCHours();
    const minutes = date.getUTCMinutes();
    const ampm = hours >= 12 ? "PM" : "AM";
    const formattedHours = hours % 12 || 12;
    const formattedMinutes = minutes < 10 ? `0${minutes}` : minutes;
    return `${formattedHours}:${formattedMinutes} ${ampm}`;
  };

  const handlePost = async () => {
    if (caption) {
      const newPostItem1 = {
        content: caption,
        username: profileData.username,
        mediaList: [],
      };

      try {
        const res = await fetch(
          `http://localhost:8080/replies/${encodeURIComponent(
            item.id
          )}/create-reply`,
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(newPostItem1),
          }
        );
        const data = await res.json();
        console.log("Response data:", data);
        if (data.id != null) {
          const newPostItem2 = {
            username: profileData.name,
            account_name: profileData.username,
            time: new Date().toLocaleTimeString(),
            captions: caption,
            comment_count: "0",
            rt_count: "0",
            like_count: "0",
            view_count: "0",
            save_count: "0",
            media: selectedFiles,
            id: data.id,
          };
          newPostItem2.time = formatTime(data.timeCreated.split(".")[0]);
          newPostItem2.captions = data.content;
          newPostItem2.rt_count = data.repostCount;
          newPostItem2.like_count = data.likeCount;
          newPostItem2.view_count = data.viewCount;
          newPostItem2.save_count = data.bookmarkCount;

          setPosts((prevPosts) => [...prevPosts, newPostItem2]);
          setCaption("");
          setSelectedFiles([]);
        } else {
          setResponse(data.message);
        }
      } catch (error) {
        setResponse("Failed to register. Please try again.");
        console.error("Error:", error);
      }
    }
  };

  const handleDeleteReply = async (postId) => {
    try {
      const res = await fetch(
        `http://localhost:8080/replies/${encodeURIComponent(postId)}/remove-reply`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      const data = await res.text();
      if (true) {
        setPosts((prevPosts) =>
          prevPosts.map((post) =>
            post.id === postId
              ? { ...post, captions: "This reply has been deleted" }
              : post
          )
        );
      } else {
        setResponse(data);
      }
    } catch (error) {
      setResponse("Failed to delete the reply. Please try again.");
      console.error("Error:", error);
    }
  };

  const [toggledIndex, setToggledIndex] = useState(null);

  const handleToggleMore = (index, event) => {
    event.stopPropagation();
    setToggledIndex(toggledIndex === index ? null : index);
  };

  const handleToggleTooltip = (event) => {
    event.stopPropagation();
    setToggledIndex(null);
  };

  return (
    <div style={{ width: "100%", height: "auto" }}>
      <div className="home-content">
        <div
          style={{
            paddingLeft: "15px",
            paddingTop: "35px",
            paddingBottom: "5px",
          }}
        >
          <img
            style={{
              height: "50px",
              width: " 50px",
            }}
          />
        </div>
        <div
          style={{
            paddingTop: "5px",
            paddingRight: "15px",
            paddingBottom: "20px",
          }}
        >
          <div>
            <p style={{ color: "rgb(113, 118, 123)" }}>
              Replying to{" "}
              <span style={{ color: "rgb(29, 155, 240)" }}>
                @{item.account_name}
              </span>
            </p>
          </div>
          <div>
            <textarea
              onInput={handleInput}
              onChange={handleCaptionChange}
              value={caption}
              placeholder="Post your reply"
              style={{
                fontSize: "24px",
                backgroundColor: "black",
                width: "100%",
                marginTop: "20px",
                outline: "none",
                resize: "none",
                overflow: "hidden",
                color: "white",
                height: "auto",
                height: "30px",
              }}
            ></textarea>
            {selectedFiles.length > 0 && (
              <ImageGridDisplay
                selectedFiles={selectedFiles}
                onRemoveFile={handleRemoveFile}
              />
            )}
          </div>
          <div className="home-content-inner-container-2"></div>
          {/* home-content-inner-container-3 */}
          <div
            style={{
              display: "grid",
              gridTemplateColumns: "1fr 1fr 100px",
              paddingTop: "20px",
            }}
          >
            {/* left side */}
            <div
              className="home-content-inner-container-3-child"
              style={{
                gap: "26px",
              }}
            >
              <ImageHandler onFileChange={handleFileChange} />
              <img
                src={GifIcon}
                style={{
                  height: "22px",
                  width: "24px",
                  cursor: "pointer",
                }}
                title="Gif"
              />
              <img
                src={EmojiIcon}
                style={{
                  height: "22px",
                  width: "24px",
                  cursor: "pointer",
                }}
                title="Emoji"
              />
              <img
                src={LocationIcon}
                style={{
                  height: "22px",
                  width: "24px",
                  cursor: "pointer",
                }}
                title="Location"
              />
            </div>
            {/* right side */}
            <div></div>
            {/* button right side */}
            <button
              onClick={handlePost}
              style={{
                backgroundColor: "rgb(29, 155, 240)",
                height: "45px",
                width: "90px",
                borderRadius: "25px",
              }}
            >
              <p
                style={{
                  fontWeight: "550",
                }}
              >
                Reply
              </p>
            </button>
          </div>
        </div>
      </div>
      {posts.map((post, index) => (
        <div key={post.id} style={{ width: "100%", position: "relative" }}>
          <Tweet
            key={post.id} // Add a unique key here
            item={post}
            onMoreButtonClick={(event) => handleToggleMore(index, event)}
          />
          {toggledIndex === index && (
            <div
              className="more-tooltip"
              onClick={(event) => handleToggleTooltip(event)}
            >
              <p
                style={{
                  padding: "7px 20px",
                  color: "red",
                  borderRadius: "40px",
                  fontWeight: "550",
                  cursor: "pointer",
                }}
                onClick={() => handleDeleteReply(post.id)}
              >
                Delete
                {post.id}
              </p>
            </div>
          )}
        </div>
      ))}
    </div>
  );
};

export default Tweetpostreply;
