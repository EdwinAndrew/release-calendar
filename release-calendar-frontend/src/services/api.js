import axios from 'axios';

// Create axios instance with base configuration
const api = axios.create({
  baseURL: 'http://localhost:8080/api',  // Spring Boot backend URL
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;