import React, { useRef } from "react";
import MediaIcon from "../../resources/icons/media-icon.png";

const ImageHandler = ({onFileChange}) => {
  const fileInputRef = useRef(null);

  const handleClick = () => {
    if (fileInputRef.current) {
      fileInputRef.current.click();
    }
  };

  const handleFileChange = (event) => {
    const files = Array.from(event.target.files);
    if (files.length > 0) {
        onFileChange(files);
      }
  };

  return (
    <div>
      <img
        src={MediaIcon}
        style={{
          height: "27px",
          width: "23px",
          cursor: "pointer", // Change cursor to pointer to indicate it's clickable
        }}
        title="Media"
        onClick={handleClick}
        alt="Media icon"
      />
      <input
        type="file"
        accept="image/*"
        multiple
        ref={fileInputRef}
        style={{ display: "none" }} // Hide the file input element
        onChange={handleFileChange}
      />
    </div>
  );
};

export default ImageHandler;
