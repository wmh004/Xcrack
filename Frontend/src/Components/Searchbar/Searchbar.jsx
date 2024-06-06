import React from "react";
import SearchIcon from "../../resources/icons/explore-icon.png";
import "./Searchbarstyles.css";

const Searchbar = () => {
  return (
    <div
      style={{
        position: "relative",
        display: "inline-block",
        width: "100%",
      }}
    >
      <img className="search-icon" src={SearchIcon} />
      <input class="search-bar" type="text" placeholder="Search" />
    </div>
  );
};
export default Searchbar;
