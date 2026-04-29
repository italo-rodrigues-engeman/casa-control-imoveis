import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { LoginPage } from './pages/LoginPage';
import { PropertiesPage } from './pages/PropertiesPage';
import { CreatePropertyPage } from './pages/CreatePropertyPage';
import { PropertyDetailPage } from './pages/PropertyDetailPage';
import { EditPropertyPage } from './pages/EditPropertyPage';
import { FavoritesPage } from './pages/FavoritesPage';
import { ManagePropertiesPage } from './pages/ManagePropertiesPage';
import { RegisterPage } from './pages/RegisterPage';
import './styles.css';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/properties" element={<PropertiesPage />} />
        <Route path="/create" element={<CreatePropertyPage />} />
        <Route path="/properties/:id" element={<PropertyDetailPage />} />
        <Route path="/properties/:id/edit" element={<EditPropertyPage />} />
        <Route path="/favorites" element={<FavoritesPage />} />
        <Route path="/manage-properties" element={<ManagePropertiesPage />} />
        <Route path="/register" element={<RegisterPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;