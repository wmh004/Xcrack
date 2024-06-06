import React, { useState } from "react";
import "./replystyles.css";
import Tweet from "../../../Components/tweet/tweet";
import Replyheader from "./replyheader";
import Tweetreply from "../../../Components/tweetreply/tweetreply";
import { useLocation, useNavigate } from "react-router-dom";
import TweetPostReply from "../../../Components/tweetpostreply/tweetpostreply";
const Reply = () => {
  const Navigate = useNavigate();
  const Location = useLocation();
  const [selectedFiles, setSelectedFiles] = useState([]);

  const { item } = Location.state;
  const replyItem = [
    {
      username: "Rep",
      account_name: "@reply1",
      time: "15h",
      captions: "erm what the sigma",
      comment_count: "2",
      rt_count: "2",
      like_count: "2",
      view_count: "161K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "Rep",
      account_name: "@reply2",
      time: "15h",
      captions: "real",
      comment_count: "2",
      rt_count: "2",
      like_count: "2",
      view_count: "161K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "Rep",
      account_name: "@reply3",
      time: "15h",
      captions: "I edge to this, skibbidy toilet",
      comment_count: "2",
      rt_count: "2",
      like_count: "2",
      view_count: "161K",
      save_count: "344K",
      media: selectedFiles,
    },
  ];

  const handleItemClick = (item) => {
    Navigate("/in/reply", { state: { item } }); /*pass item to tweet */
  };

  return (
    <div
      style={{
        paddingBottom: "100px",
      }}
    >
      <Replyheader />
      <Tweetreply item={item} />
      <TweetPostReply item={item} />
      {replyItem.map((item, index) => (
        <div
          style={{
            width: "100%",
          }}
          key={index}
          onClick={() => handleItemClick(item)}
        >
          <Tweet item={item} />
        </div>
      ))}
    </div>
  );
};

export default Reply;
