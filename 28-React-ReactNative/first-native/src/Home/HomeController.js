import React, { useState, useEffect } from "react";
import { registerRootComponent } from "expo";
import HomeView from "./HomeView";

const HomeController = () => {
  //Declarando o state information

  let information = "Teste";

  //Passando a variável information como a props info
  return <HomeView info={information} />;
};

// Somente utilizamos a declaração registerRootComponent
// quando o componente for o determinado como o inicial no
// EntryPoint do Expo, que é o caso do HomeControler.
// Caso não seja, usamos a linha "export default HomeController";
export default registerRootComponent(HomeController);
