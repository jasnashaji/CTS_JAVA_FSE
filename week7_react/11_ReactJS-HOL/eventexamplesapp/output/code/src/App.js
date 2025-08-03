import React, { useState } from 'react';
import './App.css';

function App() {
  const [count, setCount] = useState(0);
  const [amount, setAmount] = useState('');
  const [currency, setCurrency] = useState('');

  // 1. Increment handler (with 2 actions)
  const handleIncrement = () => {
    setCount(count + 1);
    alert("Hello! The value has been incremented.");
  };

  const handleDecrement = () => {
    setCount(count - 1);
  };

  // 2. Welcome handler
  const sayWelcome = (message) => {
    alert(message);
  };

  // 3. Synthetic event handler
  const handleSynthetic = () => {
    alert("I was clicked");
  };

  // 4. Currency converter
  const handleSubmit = (e) => {
    e.preventDefault();
    const converted = (parseFloat(amount) / 90).toFixed(2); // Approx 1 Euro = 90 INR
    alert(`Converting: Euro amount is €${converted}`);
  };

  return (
    <div className="App">
      <button onClick={handleIncrement}>Increment</button>
      <button onClick={handleDecrement}>Decrement</button>
      <p>Current Count: {count}</p>

      <button onClick={() => sayWelcome("Welcome")}>Say Welcome</button>
      <button onClick={handleSynthetic}>Click Me</button>

      <h2>Currency Convertor!!!</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Amount:
          <input
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
          />
        </label>
        <br />
        <label>
          Currency:
          <input
            type="text"
            value={currency}
            onChange={(e) => setCurrency(e.target.value)}
          />
        </label>
        <br />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default App;
