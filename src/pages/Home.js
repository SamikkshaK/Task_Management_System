import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => (
  <div className="container mt-5">
    <div className="card p-4 bg-light shadow">
      <h1 className="text-primary">Welcome to Task Manager</h1>
      <p>Manage your tasks efficiently with this simple app.</p>
      <Link to="/tasks" className="btn btn-primary mt-3">Go to Tasks</Link>
    </div>
  </div>
);

export default Home;
