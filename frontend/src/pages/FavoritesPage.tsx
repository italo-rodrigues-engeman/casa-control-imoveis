import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../api/api';
import { APP_NAME } from '../config/app';

export function FavoritesPage() {
  const navigate = useNavigate();

  const [favorites, setFavorites] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function loadFavorites() {
      try {
        const response = await api.get('/favorites');
        setFavorites(response.data);
      } finally {
        setLoading(false);
      }
    }

    loadFavorites();
  }, []);

  if (loading) return <p>Carregando...</p>;

  return (
    <div className="page">
      <div className="navbar">
        <h1>{APP_NAME} - Favoritos</h1>
      </div>

      <div className="container">
        <button className="button secondary" onClick={() => navigate('/properties')}>
          Voltar
        </button>

        {favorites.length === 0 ? (
          <p>Nenhum favorito encontrado</p>
        ) : (
          <div className="grid">
            {favorites.map((favorite) => (
              <div className="card" key={favorite.id}>
                <h3>{favorite.title}</h3>
                <p>{favorite.description}</p>
                <p>
                  <strong>Preço:</strong> R$ {favorite.price}
                </p>
                <p>
                  <strong>Quartos:</strong> {favorite.rooms}
                </p>
                <p>
                  <strong>Tipo:</strong> {favorite.type}
                </p>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}