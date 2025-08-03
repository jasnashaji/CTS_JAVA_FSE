import React from 'react';

function Scorebelow70({ players }) {
  const players70 = [];
  players.map((item) => {
    if (item.score <= 70) {
      players70.push(item);
    }
    return null;
  });

  return (
    players70.map((item, index) => (
      <div key={index}>
        <li>Mr. {item.name} {item.score}</li>
      </div>
    ))
  );
}

export default Scorebelow70;
