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

  const contentItem = [
    {
      username: "Mai",
      account_name: "@MaisarahMahmud",
      time: "15h",
      captions:
        "‘He knows what's good for you and when it's good for you to have it’",
      comment_count: "2",
      rt_count: "2",
      like_count: "2",
      view_count: "161K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "Johnrey",
      account_name: "@Jessx_09",
      time: "May 15",
      captions:
        "silent breakdown, because no one knows how tired and exhaust i am.",
      comment_count: "8",
      rt_count: "3K",
      like_count: "5.7K",
      view_count: "233K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "T🩺🇵🇸",
      account_name: "@tamanna_tx",
      time: "21h",
      captions: "Need a trip to Makkah and Madina to refresh my soul.",
      comment_count: "20",
      rt_count: "4.8",
      like_count: "10K",
      view_count: "269K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "🇲🇾",
      account_name: "@localrkyt",
      time: "16h",
      captions:
        "Namakan satu lauk ayam yang korang takkan jemu makan walaupun makan hari-hari 🤨😋",
      comment_count: "570",
      rt_count: "1.1K",
      like_count: "1K",
      view_count: "344K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "Mai",
      account_name: "@MaisarahMahmud",
      time: "15h",
      captions: "‘Trust’, simple word but often ignored",
      comment_count: "2",
      rt_count: "2",
      like_count: "2",
      view_count: "161K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "Johnrey",
      account_name: "@Jessx_09",
      time: "May 15",
      captions:
        "slient breadown,because no one knows how tired and exhaust i am.",
      comment_count: "8",
      rt_count: "3K",
      like_count: "5.7K",
      view_count: "233K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "T🩺🇵🇸",
      account_name: "@tamanna_tx",
      time: "21h",
      captions: "Need a trip to Makkah and Madina to refresh my soul.",
      comment_count: "20",
      rt_count: "4.8",
      like_count: "10K",
      view_count: "269K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "🇲🇾",
      account_name: "@localrkyt",
      time: "16h",
      captions:
        "Namakan satu lauk ayam yang korang takkan jemu makan walaupun makan hari-hari 🤨😋",
      comment_count: "570",
      rt_count: "1.1K",
      like_count: "1K",
      view_count: "344K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "Mai",
      account_name: "@MaisarahMahmud",
      time: "15h",
      captions:
        "‘He knows what's good for you and when it's good for you to have it’",
      comment_count: "2",
      rt_count: "2",
      like_count: "2",
      view_count: "161K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "Johnrey",
      account_name: "@Jessx_09",
      time: "May 15",
      captions:
        "slient breadown,because no one knows how tired and exhaust i am.",
      comment_count: "8",
      rt_count: "3K",
      like_count: "5.7K",
      view_count: "233K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "T🩺🇵🇸",
      account_name: "@tamanna_tx",
      time: "21h",
      captions: "Need a trip to Makkah and Madina to refresh my soul.",
      comment_count: "20",
      rt_count: "4.8",
      like_count: "10K",
      view_count: "269K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "🇲🇾",
      account_name: "@localrkyt",
      time: "16h",
      captions:
        "Namakan satu lauk ayam yang korang takkan jemu makan walaupun makan hari-hari 🤨😋",
      comment_count: "570",
      rt_count: "1.1K",
      like_count: "1K",
      view_count: "344K",
      save_count: "344K",
      media: selectedFiles,
    },

    {
      username: "Mai",
      account_name: "@MaisarahMahmud",
      time: "15h",
      captions:
        "‘He knows what's good for you and when it's good for you to have it’",
      comment_count: "2",
      rt_count: "2",
      like_count: "2",
      view_count: "161K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "Johnrey",
      account_name: "@Jessx_09",
      time: "May 15",
      captions:
        "silent breakdown, because no one knows how tired and exhaust i am.",
      comment_count: "8",
      rt_count: "3K",
      like_count: "5.7K",
      view_count: "233K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "T🩺🇵🇸",
      account_name: "@tamanna_tx",
      time: "21h",
      captions: "Need a trip to Makkah and Madina to refresh my soul.",
      comment_count: "20",
      rt_count: "4.8",
      like_count: "10K",
      view_count: "269K",
      save_count: "344K",
      media: selectedFiles,
    },
    {
      username: "🇲🇾",
      account_name: "@localrkyt",
      time: "16h",
      captions:
        "Namakan satu lauk ayam yang korang takkan jemu makan walaupun makan hari-hari 🤨😋",
      comment_count: "570",
      rt_count: "1.1K",
      like_count: "1K",
      view_count: "344K",
      save_count: "344K",
      media: selectedFiles,
    },
  ];

  const handleItemClick = (item) => {
    Navigate("/in/reply", { state: { item } }); /*pass item to tweet */
  };

  return (
    <div className="home-contents-container">
      {contentItem.map((item, index) => (
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
                Follow @{item.username}
              </p>
            </div>
          )}
        </div>
      ))}
    </div>
  );
};
export default Foryou;
