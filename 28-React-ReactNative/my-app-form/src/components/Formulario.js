import React, { useState } from "react";
import FormPopup from "./FormPopup";

const Formulario = () => {
  const [username, setUsername] = useState("Insira seu nome");
  const [email, setEmail] = useState("Digite seu e­mail");
  const [departamento, setDepartamento] = useState("");
  const [mensagem, setMensagem] = useState("Digite aqui a requisição...");
  const [error, setError] = useState("");
  const [disabledBtn, setDisabledBtn] = useState(true);
  const [trigger, setTrigger] = useState(false);

  let data = {
    username: username,
    email: email,
    departamento: departamento,
    mensagem: mensagem,
  };

  const resetDefault = () => {
    setUsername("Insira seu nome");
    setEmail("Digite seu e­mail");
    setDepartamento("");
    setMensagem("Digite aqui a requisição...");
    setError("");
    setDisabledBtn(true);
    setTrigger(false);
  };

  const onSubmit = (e) => {
    e.preventDefault();
    setTrigger(true);
    alert("Dados enviados com sucesso!");
    document.getElementById("myForm").reset();
  };

  const validateEmail = () => {
    var pattern = new RegExp(/.*@.*\..*/i);
    if (!pattern.test(document.getElementById("emailform").value)) {
      setError("*Entre com um e­mail válido.");
      setDisabledBtn(true);
    } else {
      setError("");
      setDisabledBtn(false);
    }
  };

  return (
    <>
      <form id="myForm" className="form" onSubmit={onSubmit}>
        <div className="inputfield">
          <label htmlFor="nameform"> Nome:</label>
          <input
            type="text"
            id="nameform"
            name="nameform"
            className="input"
            placeholder={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="inputfield">
          <label htmlFor="emailform">E­mail:</label>
          <div className="inputvalidate">
            <input
              type="text"
              id="emailform"
              name="emailform"
              className="input"
              placeholder={email}
              onChange={(e) => {
                validateEmail();
                setEmail(e.target.value);
              }}
            />
            <div className="text­danger">{error}</div>
          </div>
        </div>
        <div className="inputfield">
          <label htmlFor="selform">Departamento:</label>
          <div className="custom_select">
            <select
              id="selform"
              onChange={(e) => setDepartamento(e.target.value)}
            >
              <option value="">...</option>
              <option value="Financeiro">Financeiro</option>
              <option value="RH">RH</option>
              <option value="TI">TI</option>
            </select>
          </div>
        </div>
        <div className="inputfield">
          <label htmlFor="msgform">Descrição</label>
          <textarea
            id="msgform"
            className="textarea"
            placeholder={mensagem}
            onChange={(e) => setMensagem(e.target.value)}
          ></textarea>
        </div>
        <div className="inputfield">
          <button className="btn" type="submit" disabled={disabledBtn}>
            Enviar
          </button>
        </div>
      </form>
      {trigger ? (
        <FormPopup
          dados={data}
          trigger={trigger}
          removeTrigger={resetDefault}
        />
      ) : (
        <div></div>
      )}
    </>
  );
};

export default Formulario;
