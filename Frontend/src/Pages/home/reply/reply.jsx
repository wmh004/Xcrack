import React, { useEffect, useState } from "react";
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

  const [replies, setReplies] = useState([]);
  const transformData = (apiData) => {
    return apiData.map((item) => {
      return {
        username: item.name,
        account_name: item.username, // Assuming "name" from API corresponds to "account_name" in your frontend
        time: item.timeCreated, // You may need to format this according to your frontend's requirements
        captions: item.content,
        comment_count: item.replyCount,
        rt_count: item.repostCount,
        like_count: item.likeCount,
        view_count: item.viewCount,
        save_count: item.bookmarkCount, // Assuming "bookmarkCount" from API corresponds to "save_count" in your frontend
        media: selectedFiles, // You'll need to fill this based on your selected files
      };
    });
  };

  const fetchReplies = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/replies/byparentid/${encodeURIComponent(item.id)}`
      );
      const data = await response.json();
      const transformedData = transformData(data);
      setReplies(transformedData);
    } catch (error) {
      console.error("Error fetching replies:", error);
    }
  };

  useEffect(() => {
    fetchReplies();
  }, []); // Empty dependency array means it will only run once, similar to componentDidMount

  useEffect(() => {
    console.log("Replies after update:", replies);
  }, [replies]); // Empty dependency array means it will only run once, similar to componentDidMount

  const handleItemClick = (item) => {
    // Navigate("/in/reply", { state: { item } }); /*pass item to tweet */
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
      {replies.map((item, index) => (
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
