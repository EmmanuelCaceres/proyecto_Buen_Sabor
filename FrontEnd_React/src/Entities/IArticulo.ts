import IUnidadMedida from "./IUnidadMedida";
import IImagen from "./IImagen";
import ICategoria from "./ICategoria";

export default interface IArticulo{
    id:number;
    denominacion:string;
    precioVenta:string;
    unidadMedida:IUnidadMedida;
    imagenes: IImagen[];
    categoria: ICategoria; 
}