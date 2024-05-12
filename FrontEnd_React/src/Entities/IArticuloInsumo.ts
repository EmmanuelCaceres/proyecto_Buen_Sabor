import IArticulo from "./IArticulo";
import IUnidadMedida from "./IUnidadMedida";

export default interface IArticuloInsumo extends IArticulo{
    precioCompra:number;
    stockActual:number;
    stockMaximo:number;
    esParaElaborar:boolean;
    unidadMedida:IUnidadMedida;
}