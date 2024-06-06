import React, { useState, useEffect } from "react";
import "./Infostyles.css";
import Searchbar from "../Searchbar/Searchbar";
import SearchIcon from "../../resources/icons/explore-icon.png";
import MoreIcon from "../../resources/icons/more-icon-v2.png";

const Info = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);

  useEffect(() => {
    const handleResize = () => {
      setWindowWidth(window.innerWidth);
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  const toggle = () => setIsOpen(!isOpen);

  const RecommendTagItem = [
    {
      country: "Malaysia",
      tag: "Leona",
      post_count: "8,288",
    },
    {
      country: "Malaysia",
      tag: "#เชื่อว่าเพิร์ธทําได้",
      post_count: "7,574",
    },
    {
      country: "Malaysia",
      tag: "Azhar Sulaiman",
      post_count: "6,566",
    },
    {
      country: "Malaysia",
      tag: "#3M200KnongNEWza",
      post_count: "33.8K",
    },
    {
      country: "Malaysia",
      tag: "taeyong",
      post_count: "196K",
    },
    {
      country: "Malaysia",
      tag: "#AllEyesOnRafah",
      post_count: "15.9K",
    },
    {
      country: "Malaysia",
      tag: "UiTM",
      post_count: "15.2K",
    },
    {
      country: "Malaysia",
      tag: "#ThailandOpen2024",
      post_count: "1,737",
    },
    {
      country: "Malaysia",
      tag: "Johor",
      post_count: "14.6K",
    },
    {
      country: "Malaysia",
      tag: "#ILAND2",
      post_count: "20K",
    },
  ];

  const RecommendAccItem = [
    {
      username: "Khairulaming",
      account_name: "@khairulaming",
      profilepic: (
        <img
          style={{ height: "40px", width: "40px" }}
          className="sidebar-icon"
          alt={RecommendTagItem.account_name}
        />
      ),
    },
    {
      username: "Ask Rapid KL",
      account_name: "@askrapidkl",
      profilepic: (
        <img
          style={{ height: "40px", width: "40px" }}
          className="sidebar-icon"
          alt={RecommendTagItem.account_name}
        />
      ),
    },
    {
      username: "BFM89.9",
      account_name: "@BFMradio",
      profilepic: (
        <img
          style={{ height: "40px", width: "40px" }}
          className="sidebar-icon"
          alt={RecommendTagItem.account_name}
        />
      ),
    },
  ];

  return (
    <div
      className="Info-container"
      style={{
        display:
          windowWidth >= 1400
            ? "block"
            : windowWidth >= 1269
            ? "block"
            : "none",
        width:
          windowWidth >= 1400 ? "500px" : windowWidth >= 1269 ? "400px" : "0px",
      }}
    >
      <div
        style={{
          paddingLeft: "20px",
          paddingRight: "20px",
          paddingBottom: "20px",
          position: "sticky",
          top: "0",
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "center",
          gap: "25px",
          width: "90%",
        }}
      >
        <div
          style={{
            position: "sticky",
            display: "inline-block",
            width: "100%",
            top: "0",
            backgroundColor: "black",
            paddingTop: "5px",
            paddingBottom: "5px",
          }}
        >
          <Searchbar />
        </div>
        <div className="info-trends">
          <p
            style={{
              fontSize: "25px",
              fontWeight: "700",
            }}
          >
            Trends for you
          </p>
          {RecommendTagItem.map((item, index) => (
            <div className="trend-container">
              <div className="trend-child-1">
                <p
                  style={{
                    fontSize: "15px",
                    color: "rgb(113, 118, 123)",
                  }}
                >
                  Trending in {item.country}
                </p>
                <div
                  style={{
                    flex: "1",
                    display: "flex",
                    justifyContent: "flex-end",
                  }}
                >
                  <img src={MoreIcon} style={{ height: "18px" }} />
                </div>
              </div>
              <div className="trend-child-2">
                <p
                  style={{
                    fontSize: "19px",
                    fontWeight: "600",
                  }}
                >
                  {item.tag}
                </p>
              </div>
              <div className="trend-child-3">
                <p
                  style={{
                    fontSize: "15px",
                    color: "rgb(113, 118, 123)",
                  }}
                >
                  {item.post_count} posts
                </p>
              </div>
            </div>
          ))}

          <div
            style={{
              cursor: "pointer",
              fontSize: "19px",
              color: "rgb(29, 155, 240)",
            }}
          >
            Show more
          </div>
        </div>
        <div className="info-recommendations">
          <p
            style={{
              fontSize: "25px",
              fontWeight: "700",
            }}
          >
            Who to follow
          </p>
          {RecommendAccItem.map((item, index) => (
            <div className="recommend-container">
              <div
                style={{
                  cursor: "pointer",
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                {item.profilepic}
              </div>
              <div
                style={{
                  flex: "1",
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "flex-start",
                  flexDirection: "column",
                }}
              >
                <div
                  style={{
                    cursor: "pointer",
                    fontSize: "20px",
                    fontWeight: "500",
                  }}
                >
                  {item.username}
                </div>
                <div
                  style={{
                    cursor: "pointer",
                    fontSize: "19px",
                    color: "rgb(113, 118, 123)",
                  }}
                >
                  {item.account_name}
                </div>
              </div>
              <div
                style={{
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                <button
                  style={{
                    fontSize: "18px",
                    fontWeight: "500",
                    color: "black",
                    width: "95px",
                    height: "42px",
                    borderRadius: "42px",
                    cursor: "pointer",
                  }}
                >
                  Follow
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};
export default Info;
