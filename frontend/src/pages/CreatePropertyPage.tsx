import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../api/api';
import { APP_NAME } from '../config/app';

export function CreatePropertyPage() {
  const navigate = useNavigate();

  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  const [rooms, setRooms] = useState('');
  const [type, setType] = useState('');

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();

    try {
      await api.post('/properties', {
        title,
        description,
        price: Number(price),
        rooms: Number(rooms),
        type,
      });

      alert('Imóvel criado!');
      navigate('/properties');
    } catch {
      alert('Erro ao criar imóvel');
    }
  }

  return (
    <div className="page">
      <div className="navbar">
        <h1>{APP_NAME} - Criar Imóvel</h1>
      </div>

      <div className="container">
        <button className="button secondary" onClick={() => navigate('/properties')}>
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
              Salvar
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}