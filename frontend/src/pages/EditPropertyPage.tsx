import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { api } from '../api/api';

export function EditPropertyPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  const [rooms, setRooms] = useState('');
  const [type, setType] = useState('');

  useEffect(() => {
    async function loadProperty() {
      const response = await api.get(`/properties/${id}`);

      setTitle(response.data.title);
      setDescription(response.data.description);
      setPrice(String(response.data.price));
      setRooms(String(response.data.rooms));
      setType(response.data.type);
    }

    loadProperty();
  }, [id]);

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();

    try {
      await api.put(`/properties/${id}`, {
        title,
        description,
        price: Number(price),
        rooms: Number(rooms),
        type,
      });

      alert('Imóvel atualizado!');
      navigate(`/properties/${id}`);
    } catch {
      alert('Erro ao atualizar imóvel');
    }
  }

  return (
    <div className="page">
      <div className="navbar">
        <h1>Editar Imóvel</h1>
      </div>

      <div className="container">
        <button
          className="button secondary"
          onClick={() => navigate(`/properties/${id}`)}
        >
          Voltar
        </button>

        <div className="card">
          <form onSubmit={handleSubmit}>
            <input
              className="input"
              placeholder="Título"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />

            <input
              className="input"
              placeholder="Descrição"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />

            <input
              className="input"
              placeholder="Preço"
              value={price}
              onChange={(e) => setPrice(e.target.value)}
            />

            <input
              className="input"
              placeholder="Quartos"
              value={rooms}
              onChange={(e) => setRooms(e.target.value)}
            />

            <input
              className="input"
              placeholder="Tipo"
              value={type}
              onChange={(e) => setType(e.target.value)}
            />

            <button className="button" type="submit">
              Salvar alterações
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}