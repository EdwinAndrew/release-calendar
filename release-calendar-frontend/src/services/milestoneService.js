import api from './api';

// Get all milestones
export const getAllMilestones = async () => {
  const response = await api.get('/milestones');
  return response.data;
};

// Get milestones by release ID
export const getMilestonesByReleaseId = async (releaseId) => {
  const response = await api.get(`/milestones/release/${releaseId}`);
  return response.data;
};

// Create new milestone
export const createMilestone = async (milestoneData) => {
  const response = await api.post('/milestones', milestoneData);
  return response.data;
};

// Update milestone
export const updateMilestone = async (id, milestoneData) => {
  const response = await api.put(`/milestones/${id}`, milestoneData);
  return response.data;
};

// Delete milestone
export const deleteMilestone = async (id) => {
  const response = await api.delete(`/milestones/${id}`);
  return response.data;
};