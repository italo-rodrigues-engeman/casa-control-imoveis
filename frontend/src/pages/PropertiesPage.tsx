import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../api/api';
import type { Property } from '../types/Property';
import { APP_NAME } from '../config/app';

export function PropertiesPage() {
  const navigate = useNavigate();

  const [properties, setProperties] = useState<Property[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const [type, setType] = useState('');
  const [minPrice, setMinPrice] = useState('');
  const [maxPrice, setMaxPrice] = useState('');
  const [minRooms, setMinRooms] = useState('');

  const role = localStorage.getItem('role');

  async function loadProperties() {
    try {
      setLoading(true);
      setError('');

      const response = await api.get('/properties', {
        params: {
          type: type || undefined,
          minPrice: minPrice || undefined,
          maxPrice: maxPrice || undefined,
          minRooms: minRooms || undefined,
        },
      });

      setProperties(response.data);
    } catch {
      setError('Erro ao carregar imóveis');
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadProperties();
  }, []);

  if (loading) {
    return (
      <div className="page">
        <div className="navbar">
          <h1>{APP_NAME}</h1>
        </div>

        <div className="container">
          <p>Carregando imóveis...</p>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="page">
        <div className="navbar">
          <h1>{APP_NAME}</h1>
        </div>

        <div className="container">
          <p>{error}</p>

          <button className="button" onClick={loadProperties}>
            Tentar novamente
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="page">
      <div className="navbar">
        <h1>{APP_NAME}</h1>

        <button
          className="button secondary"
          onClick={() => {
            localStorage.removeItem('token');
            localStorage.removeItem('role');
            navigate('/');
          }}
        >
          Sair
        </button>
      </div>

      <div className="container">
        

        <div className="actions">
          {(role === 'ADMIN' || role === 'BROKER') && (
            <button className="button" onClick={() => navigate('/create')}>
              Criar imóvel
            </button>
          )}

          {role === 'CLIENT' && (
            <button
              className="button secondary"
              onClick={() => navigate('/favorites')}
            >
              Meus favoritos
            </button>
          )}

          {(role === 'ADMIN' || role === 'BROKER') && (
            <button
              className="button secondary"
              onClick={() => navigate('/manage-properties')}
            >
              Gestão de imóveis
            </button>
          )}
        </div>

        <div className="filters">
          <h3>Filtros</h3>

          <input
            className="input"
            placeholder="Tipo do imóvel"
            value={type}
            onChange={(e) => setType(e.target.value)}
          />

          <input
            className="input"
            placeholder="Preço mínimo"
            value={minPrice}
            onChange={(e) => setMinPrice(e.target.value)}
          />

          <input
            className="input"
            placeholder="Preço máximo"
            value={maxPrice}
            onChange={(e) => setMaxPrice(e.target.value)}
          />

          <input
            className="input"
            placeholder="Quartos mínimos"
            value={minRooms}
            onChange={(e) => setMinRooms(e.target.value)}
          />

          <button className="button" onClick={loadProperties}>
            Filtrar imóveis
          </button>
        </div>
        <h2>Imóveis disponíveis</h2>
        <p>Confira os imóveis cadastrados no sistema.</p>

        {properties.length === 0 ? (
          <p>Nenhum imóvel encontrado.</p>
        ) : (
          <div className="grid">
            {properties.map((property) => (
              <div className="card" key={property.id}>
                <h3>{property.title}</h3>
                <p>{property.description}</p>

                <p>
                  <strong>Preço:</strong> R$ {property.price}
                </p>

                <p>
                  <strong>Quartos:</strong> {property.rooms}
                </p>

                <p>
                  <strong>Tipo:</strong> {property.type}
                </p>

                <button
                  className="button"
                  onClick={() => navigate(`/properties/${property.id}`)}
                >
                  Ver detalhes
                </button>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}