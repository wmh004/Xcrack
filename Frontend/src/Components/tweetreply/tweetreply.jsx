// src/components/ContentItem.js
import React from "react";
import "./tweetreplystyles.css";
import ReplyIcon from "../../resources/icons/comment-icon.png";
import RepostIcon from "../../resources/icons/repost-icon.png";
import LikeIcon from "../../resources/icons/like-icon.png";
import BookmarkIcon from "../../resources/icons/bookmark-icon.png";
import ShareIcon from "../../resources/icons/share-icon.png";
import MoreIcon from "../../resources/icons/more-icon-v2.png";

const tweetreply = ({ item }) => {
  return (
    <div
      style={{
        paddingLeft: "15px",
        paddingRight: "15px",
        paddingTop: "25px",
        paddingBottom: "5px",
      }}
    >
      <div className="reply-content">
        <div className="reply-content-child-1">
          <div
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "flex-end",
            }}
          >
            <img
              style={{
                height: "50px",
                width: "50px",
              }}
            />
          </div>
          <div className="reply-content-child-1-child-2">
            <div
              style={{
                fontSize: "20px",
                fontWeight: "500",
              }}
            >
              {item.username}
            </div>
            <div
              style={{
                fontSize: "19px",
                color: "rgb(113, 118, 123)",
              }}
            >
              @{item.account_name}
            </div>
          </div>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "flex-end",
              gap: "10px",
            }}
          >
            <div
              style={{
                height: "40px",
                width: "95px",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                backgroundColor: "rgb(231, 233, 234)",
                color: "black",
                fontWeight: "520",
                fontSize: "17px",
                borderRadius: "40px",
              }}
            >
              Follow
            </div>
            <img
              src={MoreIcon}
              style={{
                height: "auto",
                width: "21px",
              }}
            />
          </div>
        </div>
        <div className="reply-content-child-2">
          <p
            style={{
              fontSize: "20px",
            }}
          >
            {item.captions}
          </p>
        </div>
        <div className="reply-content-child-3">
          <div className="reply-content-child-3-child">
            <div>{item.datecreated} &#183;</div>
            <div>{item.time} &#183;</div>
            <div>
              &#160;
              <span
                style={{
                  color: "white",
                  fontWeight: "520",
                }}
              >
                {item.view_count}
              </span>{" "}
              Views
            </div>
          </div>
          <div></div>
        </div>
        <div className="reply-content-child-4">
          <div className="reply-content-child-4-child">
            <img
              src={ReplyIcon}
              style={{
                height: "27px",
                width: "30px",
              }}
              title="Reply"
              alt="Reply"
            />
            <p>{item.comment_count}</p>
          </div>
          <div className="reply-content-child-4-child">
            <img
              src={RepostIcon}
              style={{
                height: "28px",
                width: "28px",
              }}
              title="Repost"
              alt="Repost"
            />
            <p>{item.rt_count}</p>
          </div>
          <div className="reply-content-child-4-child">
            <img
              src={LikeIcon}
              style={{
                height: "28px",
                width: "28px",
              }}
              title="Like"
              alt="Like"
            />
            <p>{item.like_count}</p>
          </div>
          <div className="reply-content-child-4-child">
            <img
              src={BookmarkIcon}
              style={{
                height: "30px",
                width: "30px",
              }}
              title="View"
              alt="View"
            />
            <p>{item.save_count}</p>
          </div>
          <div
            className="reply-content-child-4-child"
            style={{ justifyContent: "flex-end" }}
          >
            <img
              src={ShareIcon}
              style={{
                height: "32px",
                width: "32px",
              }}
              title="View"
              alt="View"
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default tweetreply;
