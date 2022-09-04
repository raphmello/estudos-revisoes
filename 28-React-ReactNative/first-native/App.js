import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";
import styles from "./AppStyle";

export default function App() {
  let info = "JavaScript!!!";
  return (
    <View style={styles.container}>
      <Text style={[styles.textInfo, styles.blockA]}></Text>
      <Text style={[styles.textInfo, styles.blockB]}></Text>
      <Text style={[styles.textInfo, styles.blockC]}></Text>
      {/* <Text style={styles.textInfo}>{info}</Text>
      <Text style={{ fontSize: 30, color: "red" }}>{info}</Text>
      <Text style={[styles.textInfo, { fontSize: 30, color: "red" }]}>
        {info}
      </Text> */}
      <StatusBar style="auto" />
    </View>
  );
}
