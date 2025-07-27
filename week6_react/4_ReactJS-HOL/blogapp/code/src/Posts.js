import React from 'react';
import Post from './Post';

class Posts extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
    };
  }

  // Fetch API Data
  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => response.json())
      .then(data => {
        const postObjects = data.slice(0, 10).map(item => new Post(item.id, item.title, item.body));
        this.setState({ posts: postObjects });
      })
      .catch(error => {
        console.error("Error fetching posts:", error);
      });
  }

  // Lifecycle method to load posts
  componentDidMount() {
    this.loadPosts();
  }

  // Error handling
  componentDidCatch(error, info) {
    alert("Something went wrong: " + error.toString());
    console.error("Component Error:", error, info);
  }

  // Render method
  render() {
    return (
      <div style={{ padding: '20px' }}>
        <h2>Blog Posts</h2>
        {this.state.posts.map(post => (
          <div key={post.id} style={{ marginBottom: '20px', borderBottom: '1px solid #ccc' }}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;