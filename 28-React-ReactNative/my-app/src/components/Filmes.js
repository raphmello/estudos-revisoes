import React from "react";
import Filme from "./Filme";

const filmesEstilo = {
  border: "#F78B1F solid 2px",
  borderRadius: "25px",
  backgroundColor: "#E6E6E6",
  textAlign: "Left",
  width: "500px",
  margin: "auto",
};

function Filmes() {
  return (
    <div style={filmesEstilo}>
      <Filme />
    </div>
  );
}

export default Filmes;
