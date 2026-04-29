import { useState } from 'react';
import { api } from '../api/api';
import { APP_NAME } from '../config/app';

export function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  async function handleLogin(e: React.FormEvent) {
    e.preventDefault();

    try {
      const response = await api.post('/auth/login', {
        email,
        password,
      });

      localStorage.setItem('token', response.data.token);
      localStorage.setItem('role', response.data.role);
      window.location.href = '/properties';
    } catch {
      alert('Erro no login');
    }
  }

  return (
    <div className="page">
      <div className="form">
        <h2>{APP_NAME}</h2>
        <p>Entre com seu e-mail e senha</p>

        <form onSubmit={handleLogin}>
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
            Entrar
          </button>
          <button
  className="button secondary"
  type="button"
  onClick={() => (window.location.href = '/register')}
>
  Criar conta
</button>
        </form>
      </div>
    </div>
  );
}