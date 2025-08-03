import React, { useState } from 'react';

// Login Button
function LoginButton({ onClick }) {
  return <button onClick={onClick}>Login</button>;
}

// Logout Button
function LogoutButton({ onClick }) {
  return <button onClick={onClick}>Logout</button>;
}

// Greeting component
function Greeting({ isLoggedIn }) {
  if (isLoggedIn) {
    return <h2>Welcome back</h2>;
  }
  return <h2>Please sign up.</h2>;
}

// App component
function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLoginClick = () => {
    setIsLoggedIn(true);
  };

  const handleLogoutClick = () => {
    setIsLoggedIn(false);
  };

  let button;
  if (isLoggedIn) {
    button = <LogoutButton onClick={handleLogoutClick} />;
  } else {
    button = <LoginButton onClick={handleLoginClick} />;
  }

  return (
    <div style={{ textAlign: 'center', marginTop: '50px' }}>
      <Greeting isLoggedIn={isLoggedIn} />
      {button}
    </div>
  );
}

export default App;
