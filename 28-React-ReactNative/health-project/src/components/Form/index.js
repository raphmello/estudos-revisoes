import React, { useState } from "react";
import { Text, View, TextInput, Button } from "react-native";
import ResultImc from "./ResultImc";

export default function Form() {
  const [height, setHeight] = useState(null);
  const [weight, setWeight] = useState(null);
  const [messageImc, setMessageImc] = useState("peencha o peso e altura");
  const [imc, setImc] = useState(null);
  const [textButton, setTextButton] = useState("Calcular");

  function imcCalculator() {
    return setImc((weight / (height * height)).toFixed(2));
  }

  function validationImc() {
    if (weight != null && height != null) {
      imcCalculator();
      setHeight(null);
      setWeight(null);
      setMessageImc("Seu imc é igual:");
      setTextButton("Calcular novamente");
      return;
    }
    setImc(null);
    setTextButton("Calcular");
    setMessageImc("peencha o peso e altura");
  }

  function setHeightInput(value) {
    setHeight(value.replace(",", "."));
  }

  return (
    <View>
      <View>
        <Text>Altura</Text>
        <TextInput
          placeholder="Ex. 1.75"
          keyboardType="numeric"
          onChangeText={setHeightInput}
          value={height}
        />
        <Text>Peso</Text>
        <TextInput
          placeholder="Ex. 62"
          keyboardType="numeric"
          onChangeText={setWeight}
          value={weight}
        />
        <Button title={textButton} onPress={validationImc} />
      </View>
      <ResultImc messageResultImc={messageImc} resultImc={imc} />
    </View>
  );
}
