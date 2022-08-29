import React from "react";
import Header from "./components/Header";
import { useState } from "react";
import Livros from "./components/Livros";

function App() {
  const [books, setBooks] = useState([
    {
      id: "1",
      author: "Leo Tolstoy",
      title: "Guerra e Paz",
      pages: 1296,
      year_1st_ed: 1867,
      read: "no",
    },

    {
      id: "2",
      author: "Malba Tahan",
      title: "O Homem Que Calculava",
      pages: 286,
      year_1st_ed: 1938,
      read: "no",
    },
  ]);

  return (
    <div className="container">
      <Header title="Lista de Livros" />
      <Livros livros={books} />
    </div>
  );
}

export default App;
