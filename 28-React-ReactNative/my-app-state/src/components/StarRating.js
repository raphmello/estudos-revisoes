import React, { useState, useEffect } from "react";
import { FaStar } from "react-icons/fa";

const createArray = (length) => [...Array(length)];

const Star = ({ selected = false, onSelect = (f) => f }) => (
  <FaStar color={selected ? "#ffc107" : "#b9bbc6"} onClick={onSelect} />
);

const StarRating = ({ totalStars = 5 }) => {
  const [selectedStars, setSelectedStars] = useState(0);

  const [test, setTest] = useState(0);

  useEffect(() => {
    if (test > 0) {
      alert("Teste clicado");
    }
  }, [test]);

  return (
    <div className="star">
      {createArray(totalStars).map((n, i) => (
        <Star
          key={i}
          selected={selectedStars > i}
          onSelect={() => setSelectedStars(i + 1)}
        />
      ))}
      <p>
        {selectedStars} of {totalStars} stars
      </p>
      <p>Teste {test}</p>
      <button onClick={() => setTest(test + 1)}>Clique para testar</button>
    </div>
  );
};

export default StarRating;
