export const increment = () => {
  return {
    type: "INCREMENT",
    payload: 1,
  };
};

export const decrement = () => {
  return {
    type: "DECREMENT",
    payload: 1,
  };
};

export const reset = () => {
  return {
    type: "RESET",
    payload: 0,
  };
};
