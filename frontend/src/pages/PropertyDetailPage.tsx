import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { api } from '../api/api';
import type { Property } from '../types/Property';

export function PropertyDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [property, setProperty] = useState<Property | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const role = localStorage.getItem('role');

  useEffect(() => {
    async function loadProperty() {
      try {
        const response = await api.get(`/properties/${id}`);
        setProperty(response.data);
      } catch {
        setError('Erro ao carregar imóvel');
      } finally {
        setLoading(false);
      }
    }

    loadProperty();
  }, [id]);

  async function handleFavorite() {
    try {
      await api.post('/favorites', {
        propertyId: property?.id,
      });

      alert('Imóvel favoritado!');
    } catch {
      alert('Erro ao favoritar imóvel');
    }
  }

  async function handleToggleStatus() {
    try {
      await api.patch(`/properties/${property?.id}/toggle`);

      alert('Status alterado!');
      window.location.reload();
    } catch {
      alert('Erro ao alterar status');
    }
  }

  if (loading) return <p>Carregando...</p>;
  if (error) return <p>{error}</p>;
  if (!property) return <p>Imóvel não encontrado</p>;

  return (
    <div className="page">
      <div className="navbar">
        <h1>Detalhe do Imóvel</h1>
      </div>

      <div className="container">
        <button className="button secondary" onClick={() => navigate('/properties')}>
          Voltar
        </button>

        <div className="card">
          <h2>{property.title}</h2>
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
  {(role === 'ADMIN' || role === 'BROKER') && (
    <>
      <button
        className="button"
        onClick={() => navigate(`/properties/${property.id}/edit`)}
      >
        Editar imóvel
      </button>

      <button className="button secondary" onClick={handleToggleStatus}>
        {property.active ? 'Desativar imóvel' : 'Ativar imóvel'}
      </button>
    </>
  )}

  {role === 'CLIENT' && (
    <button className="button" onClick={handleFavorite}>
      Favoritar imóvel
    </button>
  )}
</div>
        </div>
      </div>
    </div>
  );
}