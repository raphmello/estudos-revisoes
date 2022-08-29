import { createStore } from "redux";
import { combineReducers } from "redux";
import counterReducer from "../reducers/counterReducer";

const initialState = {};

const reducers = combineReducers({
  counter: counterReducer,
});

const store = createStore(
  reducers,
  initialState,
  window.REDUX_DEVTOOLS_EXTENSION && window.REDUX_DEVTOOLS_EXTENSION()
);

export default store;
