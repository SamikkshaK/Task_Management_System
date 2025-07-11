import React, { useEffect, useState } from 'react';
import api from '../api';
import TaskForm from './TaskForm';

const TaskList = () => {
  const [tasks, setTasks] = useState([]);
  const [editTask, setEditTask] = useState(null);
  const [search, setSearch] = useState('');
  const [statusFilter, setStatusFilter] = useState('All');

  const fetchTasks = async () => {
    try {
      const res = await api.get('/tasks');
      setTasks(res.data);
    } catch (err) {
      console.error('Error fetching tasks:', err);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this task?")) {
      try {
        await api.delete(`/tasks/${id}`);
        fetchTasks();
      } catch (error) {
        console.error('Delete error:', error);
        alert('Delete failed.');
      }
    }
  };

  const handleEdit = (task) => {
    setEditTask(task);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const handleFormDone = () => {
    setEditTask(null);
    fetchTasks();
  };

  const filteredTasks = tasks.filter((task) => {
    const matchesTitle = task.title.toLowerCase().includes(search.toLowerCase());
    const matchesStatus = statusFilter === 'All' || task.status === statusFilter;
    return matchesTitle && matchesStatus;
  });

  const getPriorityBadge = (priority) => {
    const color = {
      Low: 'secondary',
      Medium: 'warning',
      High: 'danger',
    };
    return <span className={`badge bg-${color[priority]}`}>{priority}</span>;
  };

  const getStatusBadge = (status) => {
    const color = {
      'Pending': 'secondary',
      'In Progress': 'info',
      'Completed': 'success',
    };
    return <span className={`badge bg-${color[status]}`}>{status}</span>;
  };

  return (
    <div className="container mt-4">
      <TaskForm onTaskAdded={handleFormDone} existingTask={editTask} />

      <div className="bg-light p-3 rounded shadow-sm mb-4 border">
        <h4 className="mb-3 text-primary"> Task Filters</h4>
        <div className="row g-3">
          <div className="col-md-6">
            <input
              type="text"
              className="form-control"
              placeholder=" Search by title..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
          </div>
          <div className="col-md-6">
            <select
              className="form-select"
              value={statusFilter}
              onChange={(e) => setStatusFilter(e.target.value)}
            >
              <option value="All">All Statuses</option>
              <option value="Pending">Pending</option>
              <option value="In Progress">In Progress</option>
              <option value="Completed">Completed</option>
            </select>
          </div>
        </div>
      </div>

      <h4 className="mb-3 text-primary"> Task List</h4>

      {filteredTasks.length === 0 ? (
        <div className="alert alert-info text-center">No tasks found</div>
      ) : (
        filteredTasks.map((task) => (
          <div className="card mb-3 shadow-sm border-left border-4 " key={task.taskId}>
            <div className="card-body bg-white">
              <div className="d-flex justify-content-between align-items-start">
                <div>
                  <h5 className="card-title text-dark">{task.title}</h5>
                  <p className="mb-1"><strong>Description:</strong> {task.description}</p>
                  <p className="mb-1"><strong>Due Date:</strong> {task.dueDate}</p>
                  <p className="mb-1">
                    <strong>Priority:</strong> {getPriorityBadge(task.priority)}{' '}
                    <strong className="ms-3">Status:</strong> {getStatusBadge(task.status)}
                  </p>
                </div>
                <div>
                  <button className="btn btn-sm btn-outline-primary me-2" onClick={() => handleEdit(task)}>
                     Edit
                  </button>
                  <button className="btn btn-sm btn-outline-danger" onClick={() => handleDelete(task.taskId)}>
                     Delete
                  </button>
                </div>
              </div>
            </div>
          </div>
        ))
      )}
    </div>
  );
};

export default TaskList;
