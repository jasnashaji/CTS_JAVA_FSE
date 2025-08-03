import React from 'react';

const books = [
  { id: 1, bname: 'Master React', price: 670 },
  { id: 2, bname: 'Deep Dive into Angular 11', price: 800 },
  { id: 3, bname: 'Mongo Essentials', price: 450 },
];

function BookDetails() {
  return (
    <div style={{ flex: 1, borderLeft: '2px solid green', padding: '10px' }}>
      <h3>Book Details</h3>
      <ul>
        {books.map((book) => (
          <div key={book.id}>
            <h4>{book.bname}</h4>
            <p>{book.price}</p>
          </div>
        ))}
      </ul>
    </div>
  );
}

export default BookDetails;
