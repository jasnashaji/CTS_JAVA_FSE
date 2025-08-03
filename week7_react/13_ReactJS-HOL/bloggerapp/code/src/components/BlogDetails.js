import React from 'react';

function BlogDetails() {
  const showBlog = true; // Example conditional flag

  if (!showBlog) return null;

  return (
    <div style={{ flex: 1, borderLeft: '2px solid green', padding: '10px' }}>
      <h3>Blog Details</h3>
      <h4>React Learning</h4>
      <p><b>Stephen Biz</b></p>
      <p>Welcome to learning React!</p>

      <h4>Installation</h4>
      <p><b>Schwedznieer</b></p>
      <p>You can install React from npm.</p>
    </div>
  );
}

export default BlogDetails;
