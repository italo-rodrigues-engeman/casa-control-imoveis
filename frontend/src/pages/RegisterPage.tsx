import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../api/api';

export function RegisterPage() {
  const navigate = useNavigate();

  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  async function handleRegister(e: React.FormEvent) {
    e.preventDefault();

    try {
      await api.post('/users', {
        name,
        email,
        password,
        role: 'CLIENT',
      });

      alert('Cadastro realizado!');
      navigate('/');
    } catch {
      alert('Erro ao cadastrar');
    }
  }

  return (
    <div className="page">
      <div className="form">
        <h2>Criar conta</h2>

        <form onSubmit={handleRegister}>
          <input
            className="input"
            placeholder="Nome"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />

          <input
            className="input"
            type="email"
            placeholder="E-mail"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <input
            className="input"
            type="password"
            placeholder="Senha"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button className="button" type="submit">
            Cadastrar
          </button>

          <button
            className="button secondary"
            type="button"
            onClick={() => navigate('/')}
          >
            Voltar para login
          </button>
        </form>
      </div>
    </div>
  );
}