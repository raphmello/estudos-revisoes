import React, { useState } from "react";
import { Text, View, CheckBox, TextInput } from "react-native";
import { FontAwesome } from "@expo/vector-icons";

const HomeView = (props) => {
  const [checked, setChecked] = useState(true);
  console.log(checked);
  return (
    <View style={{ marginTop: 130 }}>
      <Text>{props.info} - 2</Text>
      <CheckBox
        center
        title="Clicar aqui"
        value={checked}
        onValueChange={() => setChecked(!checked)}
      />
      <FontAwesome name="users" size={24} color="black" />
      <TextInput
        value={props.value}
        placeholder="useless placeholder"
        keyboardType="numeric"
      />
    </View>
  );
};

export default HomeView;
