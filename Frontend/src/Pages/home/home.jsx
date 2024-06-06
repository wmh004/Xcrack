import React from "react";
import { Route, Routes } from "react-router-dom";
import Homeheader from "./homeheader/homeheader.jsx";
import Homeuserpost from "../../Components/tweetpost/tweetpost.jsx";
import Foryou from "./foryou/foryou";
import Following from "./following/following";
import "./homestyles.css";

const Home = () => {
  return (
    <section className="home-container">
      <Homeheader />
      <Homeuserpost />
      <div>
        <Routes>
          <Route path="/" element={<Foryou />} />
          <Route path="/for-you" element={<Foryou />} />
          <Route path="/following" element={<Following />} />
        </Routes>
      </div>
    </section>
  );
};

export default Home;
