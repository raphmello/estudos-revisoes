import { Provider } from "react-redux";
import store from "./store";
import Contador from "./components/Contador";

function App() {
  return (
    <Provider store={store}>
      <div className="container">
        <header>
          <h1>Contador de cliques</h1>
        </header>
        <Contador />
      </div>
    </Provider>
  );
}

export default App;
