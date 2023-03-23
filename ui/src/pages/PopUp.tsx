import React from "react";


type PopUpProps = {
  title: string;
  description: string;
  handlePopupClose: () => void;
};

function PopUp(props: PopUpProps) {
  return (
    <div className="popup-overlay">
      <div className="popup-container">
      <button onClick={props.handlePopupClose}>X</button>
        <div className="popup-header">
        
          <h2>description of the book {props.title}</h2>
          
        </div>
        <div className="popup-body">
          <p>{props.description}</p>
        </div>
      </div>
    </div>
  );
}

export default PopUp;
