import { Link } from "react-router-dom"
export default function NavBar(){
    return(
        <nav className="navBarContainer">
            <ul className="listaNavBar">
              <li>
                <Link to={`/`} className="itemNavBar">
                    Inicio
                </Link>
              </li>
              <li>
                <Link to={`/articulos`} className="itemNavBar">
                    Articulos
                </Link>
              </li>
              <li>
                <Link to={`/articulos/categorias`} className="itemNavBar">
                    Categorias
                </Link>
              </li>
            </ul>
        </nav>
    )
}