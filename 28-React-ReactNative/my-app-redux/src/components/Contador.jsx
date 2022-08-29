import { useSelector, useDispatch } from "react-redux";
import { increment, decrement, reset } from "../actions";

const Contador = () => {
  const counter = useSelector((state) => state.counter);
  const dispatch = useDispatch();
  return (
    <div className="contador">
      <p>Cliques: {counter}</p>
      <div>
        <button className="button" onClick={() => dispatch(increment())}>
          +
        </button>
        <button className="button" onClick={() => dispatch(decrement())}>
          -
        </button>
        <button className="button" onClick={() => dispatch(reset())}>
          Reset
        </button>
      </div>
    </div>
  );
};

export default Contador;
