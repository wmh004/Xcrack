// src/components/ContentItem.js
import React, { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import "./tweetstyles.css";
import ReplyIcon from "../../resources/icons/comment-icon.png";
import RepostIcon from "../../resources/icons/repost-icon.png";
import LikeIcon from "../../resources/icons/like-icon.png";
import ViewIcon from "../../resources/icons/view-icon.png";
import BookmarkIcon from "../../resources/icons/bookmark-icon.png";
import ShareIcon from "../../resources/icons/share-icon.png";
import MoreIcon from "../../resources/icons/more-icon-v2.png";
import { useProfile } from "../../DataSets/ProfileContext";
import ImageGridDisplayTweet from "../ImageGridDisplayTweet/ImageGridDisplayTweet";

const Tweet = ({ item, onMoreButtonClick }) => {
  const location = useLocation();
  const [isHovered1, setIsHovered1] = useState(false);
  const [isHovered2, setIsHovered2] = useState(false);
  const [isHovered3, setIsHovered3] = useState(false);

  const handleItemClick = (event) => {
    event.stopPropagation();
  };

  const { profileData, setProfileData } = useProfile();
  useEffect(() => {
    if (location.state && location.state.profileData) {
      setProfileData(location.state.profileData);
    }
  }, [location.state, setProfileData]);

  return (
    <div className="home-content">
      <div
        onClick={(event) => handleItemClick(event)}
        onMouseEnter={() => setIsHovered1(true)}
        onMouseLeave={() => setIsHovered1(false)}
        className={`summary-tooltip ${
          isHovered1 || isHovered2 || isHovered3 ? "show-tooltip" : ""
        }`}
      >
        <div className="summary-tooltip-profile-top">
          <img
            style={{
              height: "60px",
              width: "60px",
            }}
            title={item.username}
          />
          <div
            style={{
              display: "flex",
              justifyContent: "flex-end",
              alignItems: "flex-start",
            }}
          >
            <button
              style={{
                padding: "7px 20px",
                color: "black",
                borderRadius: "40px",
                fontWeight: "550",
              }}
            >
              Follow
            </button>
          </div>
        </div>
        <div className="summary-tooltip-profile-middle">
          <p className="home-content-username" style={{ marginTop: "8px" }}>
            {item.username}
          </p>
          <p className="home-content-account-name" style={{ marginTop: "5px" }}>
            {item.account_name}
          </p>
          <p></p>
        </div>
        <div className="summary-tooltip-profile-bottom">bottom</div>
      </div>
      <div
        style={{
          paddingTop: "15px",
          paddingBottom: "5px",
        }}
      >
        <img
          onMouseEnter={() => setIsHovered1(true)}
          onMouseLeave={() => setIsHovered1(false)}
          style={{
            cursor: "pointer",
            height: "50px",
            width: "50px",
          }}
        />
      </div>
      <div
        style={{
          paddingRight: "15px",
          paddingTop: "15px",
          paddingBottom: "15px",
        }}
      >
        <div className="home-content-inner-container-1">
          <div
            style={{
              // flex: "1",
              display: "flex",
              flexDirection: "row",
              gap: "5px",
            }}
          >
            <p
              className="home-content-username"
              onMouseEnter={() => setIsHovered2(true)}
              onMouseLeave={() => setIsHovered2(false)}
            >
              {item.username}
            </p>
            <p
              className="home-content-account-name"
              onMouseEnter={() => setIsHovered3(true)}
              onMouseLeave={() => setIsHovered3(false)}
            >
              {item.account_name}&nbsp;&#183;
            </p>
            <p className="home-content-time">{item.time}</p>
          </div>
          <div
            style={{
              flex: "1",
              display: "flex",
              justifyContent: "flex-end",
            }}
          >
            <img
              src={MoreIcon}
              onClick={onMoreButtonClick}
              style={{
                height: "auto",
                width: "21px",
              }}
              title="More"
              alt="More"
            />
          </div>
        </div>
        <div
          className="home-content-inner-container-2"
          style={{ whiteSpace: "pre-wrap" }}
        >
          <p>{item.captions}</p>
          <ImageGridDisplayTweet selectedFiles={item.media} />
        </div>
        <div className="home-content-inner-container-3">
          <div className="home-content-inner-container-3-child">
            <img
              src={ReplyIcon}
              style={{
                height: "22px",
                width: "24px",
              }}
              title="Reply"
              alt="Reply"
            />
            <p>{item.comment_count}</p>
          </div>
          <div className="home-content-inner-container-3-child">
            <img
              src={RepostIcon}
              style={{
                height: "22px",
                width: "24px",
              }}
              title="Repost"
              alt="Repost"
            />
            <p>{item.rt_count}</p>
          </div>
          <div className="home-content-inner-container-3-child">
            <img
              src={LikeIcon}
              style={{
                height: "22px",
                width: "24px",
              }}
              title="Like"
              alt="Like"
            />
            <p>{item.like_count}</p>
          </div>
          <div className="home-content-inner-container-3-child">
            <img
              src={ViewIcon}
              style={{
                height: "22px",
                width: "24px",
              }}
              title="View"
              alt="View"
            />
            <p>{item.view_count}</p>
          </div>
          <div className="home-content-inner-container-3-child">
            <img
              src={BookmarkIcon}
              style={{
                height: "25px",
                width: "25px",
              }}
              title="Bookmark"
              alt="Bookmark"
            />
            <img
              src={ShareIcon}
              style={{
                height: "27px",
                width: "27px",
              }}
              title="Share"
              alt="Share"
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Tweet;
