import { StyleSheet, View } from "react-native";
// import Title from "./src/components/Title";
// import Main from "./src/components/Main";
import ProductCard from "./src/components/RestaurantCard";

export default function App() {
  const imgPath = require("./assets/cheeseburger-bread_720x560.jpg");
  const calories = 246;
  const price = 10.0;
  return (
    <View style={styles.container}>
      {/* <Title />
      <Main /> */}
      <ProductCard imgSource={imgPath} calories={calories} price={price} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
