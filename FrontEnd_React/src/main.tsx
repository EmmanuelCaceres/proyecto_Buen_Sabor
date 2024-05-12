import React from 'react'
import ReactDOM from 'react-dom/client'
import {createBrowserRouter,RouterProvider} from "react-router-dom";
import Root from './Components/Root.tsx';
import ErrorPage from './Components/ErrorPage.tsx';
import SaveArticulo from './Components/SaveArticulo.tsx';
import GrillaArticulo from './Components/GrillaArticulo.tsx';
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';

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
      }
    ],
  },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
