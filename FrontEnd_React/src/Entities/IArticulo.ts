import IUnidadMedida from "./IUnidadMedida";
import IImagenArticulo from "./IImagenArticulo";
import ICategoria from "./ICategoria";

export default interface IArticulo{
    id:number;
    denominacion:string;
    precioVenta:string;
    unidadMedida:IUnidadMedida;
    imagenes: IImagenArticulo[];
    categoria: ICategoria; 
}