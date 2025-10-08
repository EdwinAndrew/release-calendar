import { useState, useEffect } from 'react';
import { getAllReleases } from './services/releaseService';

function App() {
  const [releases, setReleases] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchReleases = async () => {
      try {
        const data = await getAllReleases();
        setReleases(data);
        setLoading(false);
      } catch (err) {
        setError(err.message);
        setLoading(false);
      }
    };

    fetchReleases();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h1>Release Calendar</h1>
      <p>Found {releases.length} releases</p>
      <ul>
        {releases.map(release => (
          <li key={release.id}>
            {release.releaseWindow} ({release.startDate} to {release.endDate})
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;