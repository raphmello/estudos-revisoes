import React from "react";

const lista_filmes = [
  "Episódio IV Uma nova esperança (1977)",
  "Episódio V O Império Contra-Ataca (1980)",
  "Episódio VI O Retorno de Jedi (1983)",
  "Episódio I A Ameaça Fantasma (1999)",
  "Episódio II Ataque dos Clones (2002)",
  "Episódio III A Vingança dos Sith (2005)",
  "Episódio VII O Despertar da Força (2015)",
  "Rogue One: Uma História Star Wars (2016)",
  "Episódio VIII Os Últimos Jedi (2017)",
  "Solo: Uma História Star Wars (2018)",
  "Episódio IX A Ascensão Skywalker (2019)",
];

const myArray = [];

lista_filmes.forEach((item, i) => myArray.push(<li key={item + i}>{item}</li>));
const myList = <ul>{myArray}</ul>;

function Filme() {
  return <div>{myList}</div>;
}

export default Filme;
