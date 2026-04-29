# 🏠 Casa Control Imóveis

Sistema full-stack para gestão e consulta de imóveis com autenticação JWT, controle de acesso por perfil e persistência em MongoDB.

---

## 📌 Descrição

O **Casa Control Imóveis** é uma aplicação web que permite:

* Gestão de imóveis por corretores e administradores
* Consulta e favoritação de imóveis por clientes
* Controle de acesso baseado em perfil (ADMIN, BROKER, CLIENT)

Arquitetura baseada em separação de responsabilidades (clean architecture).

---

## 🚀 Tecnologias

### Backend

* Java 17+
* Spring Boot
* Spring Security
* JWT
* MongoDB
* Lombok

### Frontend

* React
* TypeScript
* Vite
* React Router
* Axios

### Infraestrutura

* Docker
* Docker Compose

---

## 🧱 Estrutura do Projeto

```
casa-control-imoveis
├── backend
│   ├── Dockerfile
│   └── Spring Boot API
├── frontend
│   ├── Dockerfile
│   └── React App
└── docker-compose.yml
```

---

## ▶️ Como rodar o projeto (RECOMENDADO)

### ✅ Pré-requisitos

* Docker instalado

---

### 🚀 Rodar tudo com 1 comando

```bash
docker compose up --build
```

---

### 🌐 Acessos

```
Frontend → http://localhost:5173
Backend  → http://localhost:8080
MongoDB  → localhost:27017
```

---

## ⚠️ Importante

* Não é necessário instalar Java, Node ou Mongo
* Tudo roda via Docker
* O banco já sobe automaticamente

---

## 🔹 Rodar manualmente (modo antigo)

### Mongo

```bash
docker run -d --name casa-control-mongo -p 27017:27017 mongo:latest
```

### Backend

```bash
cd backend
mvnw.cmd spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

---

## 👥 Perfis de Usuário

### 🛠 ADMIN

* Criar usuários
* Criar corretores
* Criar imóveis
* Gerenciar imóveis

### 🏢 BROKER

* Criar imóveis
* Editar próprios imóveis
* Ativar/desativar imóveis

### 👤 CLIENT

* Listar imóveis
* Ver detalhes
* Favoritar imóveis

---

## ⚙️ Funcionalidades

* 🔐 Login com JWT
* 👤 Cadastro de cliente
* 🏠 CRUD de imóveis
* 🔎 Filtros
* ⭐ Favoritar imóveis
* 📋 Listagem de favoritos
* 🧭 Navegação protegida
* 💾 Persistência com MongoDB

---

## 🔐 Endpoints principais

| Método | Endpoint                | Descrição        |
| ------ | ----------------------- | ---------------- |
| POST   | /auth/login             | Login            |
| POST   | /users                  | Criar usuário    |
| GET    | /properties             | Listar imóveis   |
| POST   | /properties             | Criar imóvel     |
| PUT    | /properties/{id}        | Atualizar imóvel |
| PATCH  | /properties/{id}/toggle | Ativar/desativar |
| POST   | /favorites              | Favoritar imóvel |
| GET    | /favorites              | Listar favoritos |

---

## 🧪 Usuários de teste

### Criar ADMIN/BROKER (Postman)

```json
{
  "name": "Admin",
  "email": "admin@test.com",
  "password": "123456",
  "role": "ADMIN"
}
```

```json
{
  "name": "Broker",
  "email": "broker@test.com",
  "password": "123456",
  "role": "BROKER"
}
```

---

## 📈 Próximos passos

* Deploy em produção
* Melhorias de UI/UX
* Testes automatizados
* Paginação

---

## 🧠 Autor

Italo Rodrigues
