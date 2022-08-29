import "./App.css";
import { BrowserRouter as Router, Link, Routes, Route } from "react-router-dom";

const Home = () => {
  return (
    <div className="container">
      <h1>Bem-vindo ao Senac!</h1>
      <div className="showcase">
        <p>Esta é a página Home.</p>
      </div>
    </div>
  );
};

const About = () => {
  return (
    <div className="container">
      <h1>Sobre nós</h1>
      <div className="showcase">
        <div>
          <h3>História</h3>
          <p>Esta é a página Sobre nós.</p>
        </div>
      </div>
    </div>
  );
};

const Courses = () => {
  return (
    <div className="container">
      <h1>Conheça nossos cursos</h1>
      <div className="showcase">
        <div>
          <h3>Desenvolvimento de API REST</h3>
        </div>
        <div>
          <h3>Single Page Application</h3>
        </div>
      </div>
    </div>
  );
};

function App() {
  return (
    <Router>
      <div>
        <nav className="main-nav">
          <ul className="main-menu">
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/about">Sobre Nós</Link>
            </li>
            <li>
              <Link to="/courses">Cursos</Link>
            </li>
          </ul>
        </nav>

        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/about" element={<About />} />
          <Route exact path="/courses" element={<Courses />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
