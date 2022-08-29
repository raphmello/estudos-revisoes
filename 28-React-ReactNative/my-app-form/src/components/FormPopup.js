import React, { useState } from "react";
import Popup from "reactjs-popup";

const contentStyle = {
  position: "absolute",
  top: "5%",
  left: "5%",
  right: "5%",
  bottom: "auto",
  border: "2px solid #ccc",
  background: "#fff",
  WebkitOverflowScrolling: "touch",
  borderRadius: "4px",
  outline: "none",
  padding: "20px",
};

const FormPopup = ({ dados, trigger, removeTrigger }) => {
  const { username, email, departamento, mensagem } = dados;
  const [open, setOpen] = useState(trigger);

  const closeModal = () => {
    setOpen(false);
    removeTrigger();
  };

  return (
    <div>
      <Popup
        open={open}
        closeOnDocumentClick
        onClose={closeModal}
        modal
        contentStyle={contentStyle}
      >
        {(close) => (
          <div className="content">
            <button className="close" onClick={close}>
              &times;
            </button>
            <h1 className="header">Verifique os seus dados!</h1>
            <div className="showcase">
              <strong>Nome:</strong>
              {username}
              <strong>E­mail:</strong>
              {email}
              <strong>Departamento:</strong> {departamento}
              <strong>Mensagem:</strong>
              {mensagem}
            </div>
            <button className="btn" onClick={() => close()}>
              Enviar
            </button>
          </div>
        )}
      </Popup>
    </div>
  );
};

export default FormPopup;
