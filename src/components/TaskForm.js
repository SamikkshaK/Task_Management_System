import React, { useEffect, useState } from 'react';
import api from '../api';

const TaskForm = ({ onTaskAdded, existingTask }) => {
  const [task, setTask] = useState({
    title: '',
    description: '',
    dueDate: '',
    priority: '',
    status: ''
  });

  useEffect(() => {
    if (existingTask) {
      setTask({ ...existingTask });
    }
  }, [existingTask]);

  const handleChange = (e) => {
    setTask({ ...task, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (task.taskId) {
        await api.put(`/tasks/${task.taskId}`, task);
        alert('Task updated!');
      } else {
        await api.post('/tasks', task);
        alert('Task added!');
      }
      setTask({ title: '', description: '', dueDate: '', priority: '', status: '' });
      onTaskAdded();
    } catch (error) {
      console.error('Error submitting task:', error);
      alert('Submit failed');
    }
  };

  return (
    <div className="card p-4 shadow mb-4">
      <h4>{task.taskId ? 'Edit Task' : 'Add New Task'}</h4>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <input type="text" name="title" value={task.title} onChange={handleChange} className="form-control" placeholder="Title" required />
        </div>
        <div className="mb-3">
          <textarea name="description" value={task.description} onChange={handleChange} className="form-control" placeholder="Description" required />
        </div>
        <div className="mb-3">
          <input type="date" name="dueDate" value={task.dueDate} onChange={handleChange} className="form-control" required />
        </div>
        <div className="mb-3">
          <select name="priority" value={task.priority} onChange={handleChange} className="form-select" required>
            <option value="">Select Priority</option>
            <option>Low</option>
            <option>Medium</option>
            <option>High</option>
          </select>
        </div>
        <div className="mb-3">
          <select name="status" value={task.status} onChange={handleChange} className="form-select" required>
            <option value="">Select Status</option>
            <option>Pending</option>
            <option>In Progress</option>
            <option>Completed</option>
          </select>
        </div>
        <button className="btn btn-success">{task.taskId ? 'Update Task' : 'Add Task'}</button>
      </form>
    </div>
  );
};

export default TaskForm;
