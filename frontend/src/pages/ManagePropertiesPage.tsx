import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../api/api';
import type { Property } from '../types/Property';
import { APP_NAME } from '../config/app';

export function ManagePropertiesPage() {
  const navigate = useNavigate();

  const [properties, setProperties] = useState<Property[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  async function loadProperties() {
    try {
      setLoading(true);
      const response = await api.get('/properties');
      setProperties(response.data);
    } catch {
      setError('Erro ao carregar imóveis');
    } finally {
      setLoading(false);
    }
  }

  async function toggleStatus(id: string) {
    try {
      await api.patch(`/properties/${id}/toggle`);
      await loadProperties();
    } catch {
      alert('Erro ao alterar status');
    }
  }

  useEffect(() => {
    loadProperties();
  }, []);

  if (loading) return <p>Carregando...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="page">
      <div className="navbar">
        <h2>{APP_NAME}</h2>
      </div>

      <div className="container">
        <button className="button secondary" onClick={() => navigate('/properties')}>
          Voltar
        </button>

        {properties.length === 0 ? (
          <p>Nenhum imóvel encontrado</p>
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
                <p>
                  <strong>Status:</strong> {property.active ? 'Ativo' : 'Inativo'}
                </p>

                <div className="actions">
                  <button
                    className="button"
                    onClick={() => navigate(`/properties/${property.id}`)}
                  >
                    Detalhes
                  </button>

                  <button
                    className="button"
                    onClick={() => navigate(`/properties/${property.id}/edit`)}
                  >
                    Editar
                  </button>

                  <button
                    className="button secondary"
                    onClick={() => toggleStatus(property.id)}
                  >
                    {property.active ? 'Desativar' : 'Ativar'}
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}