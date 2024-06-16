import React, { useState, useEffect } from "react";
import XcrackLogo from "../../resources/twitter/xcrack-logo.jpg";
import { useNavigate, useLocation } from "react-router-dom";

const InitialTagging = () => {
  const [response, setResponse] = useState("");
  const [data, setData] = useState("");
  const location = useLocation();
  const username = location.state?.username;

  const [isDetailsFilled, setIsDetailsFilled] = useState(false);
  const Navigate = useNavigate();

  const handleItemClick = () => {
    Navigate("/verification/user/create-account");
  };
  const [selectedTags, setSelectedTags] = useState([]);

  const TagItems = [
    {
      hashtag: data,
    },
  ];
  const [isVisible, setIsVisible] = useState(false);
  useEffect(() => {
    if (response) {
      setIsVisible(true);
      setTimeout(() => {
        setIsVisible(false);
        setResponse("");
      }, 2000); // Delay and hide after 300ms
    }
  }, [response]);
  // Button click handler
  const handleNextButtonClick = async () => {
    if (isDetailsFilled) {
      // All fields are filled, navigate to the next page
      try {
        const res = await fetch(
          `http://localhost:8080/signUp/initialHashtag/${encodeURIComponent(username)}`,
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
          }
        );
        const datadb = await res.text();
        if (datadb === "Hashtag chosen.") {
          Navigate("/verification/login");
        } else {
          setResponse(datadb);
        }
      } catch (error) {
        setResponse("Failed to transfer the tagging. Please try again. e");
        console.error("Error:", error);
      }
    }
  };

  const handleTagClick = (tag) => {
    setSelectedTags((prevSelectedTags) => {
      const updatedTags = prevSelectedTags.includes(tag)
        ? prevSelectedTags.filter((t) => t !== tag) // Unselect if already selected
        : [...prevSelectedTags, tag]; // Select if not selected

      // Update isDetailsFilled based on the number of selected tags
      setIsDetailsFilled(updatedTags.length === 5);
      setData(updatedTags);

      return updatedTags;
    });
  };

  const tags = [
    "#TechNews", // Technology news
    "#Innovation", // Technological innovations
    "#ScienceDaily", // Daily science updates
    "#HealthTips", // Health advice
    "#FitnessGoals", // Fitness and exercise
    "#SportsUpdate", // Sports news
    "#EntertainmentBuzz", // Entertainment updates
    "#MovieReviews", // Film reviews
    "#TravelDiaries", // Travel stories
    "#Foodie", // Food and recipes
    "#CulinaryDelights", // Gourmet cooking
    "#EducationMatters", // Educational content
    "#FinanceTips", // Financial advice
    "#InvestmentHacks", // Investment strategies
    "#FashionTrends", // Fashion updates
    "#StyleInspo", // Style inspiration
    "#ArtInspiration", // Artistic ideas
    "#HistoryToday", // Historical events
    "#SexEducation", // Sexual health
    "#DigitalLife", // Living in the digital age
    "#EcoFriendly", // Environmental consciousness
    "#MentalHealth", // Mental wellness
    "#TravelGoals", // Travel aspirations
    "#cienceFacts", // Interesting science facts
    "#WellnessJourney", // Overall wellness
  ];

  return (
    <div className="create-acc-container-2">
      <div className="filler-3">
        <img className="create-acc-logo" src={XcrackLogo} />
        <div
          onClick={() => handleItemClick()}
          className="create-acc-close-button"
        >
          X
        </div>
      </div>
      <div
        className="create-acc-contents"
        style={{
          flex: "1fr",
          boxSizing: "border-box",
          padding: "5px 20px",
        }}
      >
        <h2>Choose Your Tags</h2>
        {/* {data}yes */}
        <div className="tag-grid">
          {tags.map((tag, index) => (
            <div
              key={index}
              className={`tag-item ${
                selectedTags.includes(tag) ? "selected" : ""
              }`}
              onClick={() => handleTagClick(tag)}
            >
              {tag}
            </div>
          ))}
        </div>
      </div>
      <div
        className="filler-3"
        style={{
          boxSizing: "border-box",
          padding: "5px 20px",
        }}
      >
        <button
          onClick={() => handleNextButtonClick()}
          className={`forgot-pass-next-button ${
            isDetailsFilled ? "active" : ""
          }`}
        >
          Next
        </button>
      </div>
      <div
        style={{
          position: "fixed",
          bottom: isVisible ? "40px" : "",
          backgroundColor: "rgb(29, 155, 240)",
          padding: "10px 20px",
          borderRadius: "10px",
          transition: "bottom 0.3s ease-in-out",
          display: isVisible ? "" : "none",
        }}
      >
        {response}
      </div>
    </div>
  );
};
export default InitialTagging;
