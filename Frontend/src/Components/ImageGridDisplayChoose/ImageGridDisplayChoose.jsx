import React from "react";

const ImageGridDisplayChoose = ({ selectedFiles, onRemoveFile }) => {
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
            <button
              onClick={() => onRemoveFile(index)}
              style={{
                position: "absolute",
                top: "5px",
                right: "5px",
                background: "black",
                color: "white",
                border: "none",
                borderRadius: "50%",
                width: "25px",
                height: "25px",
                cursor: "pointer",
              }}
            >
              X
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ImageGridDisplayChoose;
