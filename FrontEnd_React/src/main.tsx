import React from 'react'
import ReactDOM from 'react-dom/client'
import {createBrowserRouter,RouterProvider} from "react-router-dom";
import Root from './Components/Root.tsx';
import ErrorPage from './Components/ErrorPage.tsx';
import SaveArticulo from './Components/SaveArticuloManufacturado.tsx';
import GrillaArticulo from './Components/GrillaArticuloManufacturado.tsx';
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import GrillaEmpresa from './Components/GrillaEmpresa.tsx';
import GrillaCategoria from './Components/GrillaCategoria.tsx';
import GrillaEmpleado from './Components/GrillaEmpleado.tsx';
import GrillaRol from './Components/GrillaRol.tsx';
import GrillaPromocion from './Components/GrillaPromocion.tsx';
import GrillaInsumo from './Components/GrillaInsumo.tsx';
import SaveInsumo from './Components/SaveInsumo.tsx';
import SaveCategoria from './Components/SaveCategoria.tsx';
import HomeEmpresa from './Components/HomeEmpresa.tsx';
import Sucursales from './Components/Sucursales.tsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root/>,
    errorElement:<ErrorPage/>,
    children: [
      {
        path: "articulos",
        element: <GrillaArticulo />,
      },
      {
        path:"articulos/save/:id",
        element:<SaveArticulo/>
      },
      {
        path:"insumos/save/:id",
        element:<SaveInsumo/>
      },
      {
        path:"categorias/save/:id",
        element:<SaveCategoria/>
      },
      {
        path:"empresas",
        element:<GrillaEmpresa/>
      },
      {
        path:"categorias",
        element:<GrillaCategoria/>
      },
      {
        path:"empleados",
        element:<GrillaEmpleado/>
      },
      {
        path:"roles",
        element:<GrillaRol/>
      },
      {
        path:"promociones",
        element:<GrillaPromocion/>
      },
      {
        path:"insumos",
        element:<GrillaInsumo/>
      }
    ],
    
  },
  {
    path:"/homeEmpresa",
    element:<HomeEmpresa/>
  },
  {
    path:"/sucursales/:id",
    element:<Sucursales></Sucursales>
  }
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
