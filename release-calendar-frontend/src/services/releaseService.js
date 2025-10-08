import api from './api';


// Get all releases
export const getAllReleases = async () => {
    const response = await api.get('/releases');
    return response.data;
};

// Get single release by ID
export const getReleaseById = async (id) => {
  const response = await api.get(`/releases/${id}`);
  return response.data;
};

// Create new release
export const createRelease = async (releaseData) => {
  const response = await api.post('/releases', releaseData);
  return response.data;
};

// Update release
export const updateRelease = async (id, releaseData) => {
  const response = await api.put(`/releases/${id}`, releaseData);
  return response.data;
};

// Delete release
export const deleteRelease = async (id) => {
  const response = await api.delete(`/releases/${id}`);
  return response.data;
};
