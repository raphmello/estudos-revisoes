import { StyleSheet } from "react-native";
const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    flex: 1,
    marginTop: 60,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  textInfo: {
    fontSize: 30,
    color: "red",
  },
  blockA: {
    flex: 1,
    width: "100%",
    height: "100%",
    backgroundColor: "powderblue",
  },
  blockB: {
    flex: 2,
    width: "100%",
    height: "100%",
    backgroundColor: "skyblue",
  },
  blockC: {
    flex: 3,
    width: "100%",
    height: "100%",
    backgroundColor: "steelblue",
  },
});
export default styles;
