import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import NotFound from './pages/NotFound';
import Login from './pages/Login';
import TaskList from './components/TaskList';

const App = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const username = localStorage.getItem('username');
    const password = localStorage.getItem('password');
    setIsAuthenticated(!!username && !!password);
  }, []);

  return (
    <Router>
      <Navbar />
      <Routes>
        
        <Route path="/" element={<Login onLogin={() => setIsAuthenticated(true)} />} />

        
        <Route
          path="/home"
          element={isAuthenticated ? <Home /> : <Navigate to="/" />}
        />

        
        <Route
          path="/tasks"
          element={isAuthenticated ? <TaskList /> : <Navigate to="/" />}
        />

        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
};

export default App;
