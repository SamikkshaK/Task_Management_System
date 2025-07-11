import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Navbar = () => {
  const navigate = useNavigate();
  const username = localStorage.getItem('username');

  const handleLogout = () => {
    localStorage.removeItem('username');
    localStorage.removeItem('password');
    navigate('/');
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary px-4 sticky-top">
      <Link className="navbar-brand d-flex align-items-center" to="/">
        <img
          src="/logo.png"
          alt="Logo"
          width="35"
          height="35"
          className="me-2 rounded-circle"
        />
        <strong>TaskManager</strong>
      </Link>

      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarContent"
        aria-controls="navbarContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>

      <div className="collapse navbar-collapse" id="navbarContent">
        {username && (
          <ul className="navbar-nav ms-3">
            <li className="nav-item">
              <Link className="nav-link" to="/home">Home</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/tasks">Tasks</Link>
            </li>
          </ul>
        )}

        <div className="ms-auto">
          {!username ? (
            <Link to="/" className="btn btn-outline-light">Login</Link>
          ) : (
            <button onClick={handleLogout} className="btn btn-outline-light">Logout</button>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
