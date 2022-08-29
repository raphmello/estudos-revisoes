import React from "react";
import PropTypes from "prop-types";
import LivroItem from "./LivroItem";

const Livros = ({ livros }) => {
  return livros.map((livro) => <LivroItem key={livro.id} livro={livro} />);
};

// Validando prop com propTypes e tornando-a obrigatória
Livros.propTypes = {
  livros: PropTypes.array.isRequired,
};

export default Livros;
