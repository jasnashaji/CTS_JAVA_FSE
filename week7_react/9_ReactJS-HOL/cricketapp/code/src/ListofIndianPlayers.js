import React from 'react';

function ListofIndianPlayers({ IndianPlayers }) {
  return (
    IndianPlayers.map((item, index) => (
      <div key={index}>
        <li>Mr. {item}</li>
      </div>
    ))
  );
}

export default ListofIndianPlayers;
