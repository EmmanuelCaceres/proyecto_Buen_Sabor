import { useState } from "react";
import { Link } from "react-router-dom";

export default function NavBar() {
    const [isArticulosOpen, setIsArticulosOpen] = useState(false);

    const ArticulosMenu = () => {
        setIsArticulosOpen(!isArticulosOpen);
    };

    return (
        <nav className="navBarContainer">
            <ul className="listaNavBar">
                <li>
                    <Link to={`/`} className="itemNavBar">
                        Inicio
                    </Link>
                </li>
                <li>
                    <Link to={`/empresas`} className="itemNavBar">
                        Empresa
                    </Link>
                </li>
                <li>
                    <div className="itemNavBar" onClick={ArticulosMenu}>
                        Artículos
                    </div>
                    {isArticulosOpen && (
                        <ul className="subMenu">
                            <li>
                                <Link to={`/articulos`} className="itemNavBar">
                                    Lista de artículos
                                </Link>
                            </li>
                            <li>
                                <Link to={`/articulos/categorias`} className="itemNavBar">
                                    Categorías
                                </Link>
                            </li>
                        </ul>
                    )}
                </li>
                <li>
                    <Link to={`/promociones`} className="itemNavBar">
                        Promociones
                    </Link>
                </li>
            </ul>
        </nav>
    );
}
