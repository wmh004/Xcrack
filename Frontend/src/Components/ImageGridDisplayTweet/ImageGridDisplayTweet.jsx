import React from "react";

const ImageGridDisplayTweet = ({ selectedFiles }) => {
  return (
    <div style={{ marginTop: "10px" }}>
      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fit, minmax(45%, 1fr))",
          gap: "10px",
        }}
      >
        {selectedFiles.map((file, index) => (
          <div key={index} style={{ position: "relative" }}>
            <img
              src={URL.createObjectURL(file)}
              alt={`Selected ${index}`}
              style={{
                width: "100%",
                height: "auto",
                display: "block",
                borderRadius: "20px",
              }}
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default ImageGridDisplayTweet;
