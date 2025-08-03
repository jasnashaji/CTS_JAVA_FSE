import React from 'react';
import './App.css';

function App() {
  const element = "Office Space";

  // ✅ Correct office image URL
  const sr = "https://thumbs.dreamstime.com/b/office-work-place-5879959.jpg";

  const jsxatt = <img src={sr} width="40%" alt="Office Space" />;

  const ItemName = {
    Name: "DBS",
    Rent: 50000,
    Address: "Chennai"
  };

  // Color logic based on rent
  let colors = [];
  if (ItemName.Rent <= 60000) {
    colors.push("textRed");
  } else {
    colors.push("textGreen");
  }

  return (
    <div className="App">
      <h1>{element}, at Affordable Range</h1>
      {jsxatt}
      <h2>Name: {ItemName.Name}</h2>
      <h3 className={colors.join(" ")}>Rent: Rs. {ItemName.Rent}</h3>
      <h3>Address: {ItemName.Address}</h3>
    </div>
  );
}

export default App;
