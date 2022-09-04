import React from "react";
import { View, StyleSheet, Dimensions, Image, Text } from "react-native";

const ProductCard = (props) => {
  return (
    <View style={styles.cardContainer}>
      <Image style={styles.imageStyle} source={props.imgSource} />
      <View style={styles.infoStyle}>
        <Text style={styles.titleStyle}>Beef Burguer</Text>
        <Text style={styles.caloriesStyle}>
          {props.calories.toFixed(0)} Calories{" "}
        </Text>
        <Text style={styles.priceStyle}>${props.price.toFixed(2)} </Text>
      </View>
    </View>
  );
};

const deviceWidth = Math.round(Dimensions.get("window").width);
const radius = 20;
const styles = StyleSheet.create({
  cardContainer: {
    width: deviceWidth - 25,
    backgroundColor: "#FFF",
    height: 230,
    borderRadius: radius,
    shadowColor: "#000",
    shadowOffset: {
      width: 5,
      height: 5,
    },
    shadowOpacity: 0.75,
    elevation: 9,
    shadowRadius: 5,
  },
  imageStyle: {
    height: 130,
    width: deviceWidth - 25,
    borderTopLeftRadius: radius,
    borderTopRightRadius: radius,
    opacity: 0.9,
  },
  titleStyle: {
    fontSize: 36,
    fontWeight: "800",
  },
  caloriesStyle: {
    fontWeight: "600",
    color: "grey",
    marginTop: 5,
  },
  priceStyle: {
    fontWeight: "800",
    fontSize: 20,
    color: "gold",
    marginTop: 5,
  },
  infoStyle: {
    marginHorizontal: 10,
    marginVertical: 5,
  },
});

export default ProductCard;
