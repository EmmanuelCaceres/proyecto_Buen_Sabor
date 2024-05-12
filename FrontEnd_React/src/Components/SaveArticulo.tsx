import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"
import { Link } from "react-router-dom";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import IArticuloManufacturado from "../Entities/IArticuloManufacturado";
import IArticuloInsumo from "../Entities/IArticuloInsumo";
import arrow_left from "../assets/arrow-circle-left-svgrepo-com.svg";
import ArticuloManufacturadoService from "../Functions/Services/ArticuloManufacturadoService";
import ArticuloInsumoService from "../Functions/Services/ArticuloInsumoService";
import IArticuloManufacturadoDetalles from "../Entities/IArticuloManufacturadoDetalle";

export default function SaveArticulo() {
    const { id } = useParams();

    const [inputValue, setInputValue] = useState('');
    const [insumos, setInsumos] = useState<IArticuloInsumo[]>([]);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [articuloManufacturado, setArticulosManufacturado] = useState<IArticuloManufacturado>(
        {
            id: Number(id),
            denominacion: '',
            precioVenta: '',
            unidadMedida: {
                id: 0,
                denominacion: '',
            },
            imagenes: {
                id: 0,
                url: ''
            },
            categoria: {
                id: 0,
                denominacion: ''
            },
            descripcion: '',
            tiempoEstimadoMinutos: 0,
            preparacion: '',
            articuloManufacturadoDetalles: []
        }
    );

    const getArticuloManufacturado = async (baseUrl:string,id:number) => {
        const result = new  ArticuloManufacturadoService(baseUrl);
        await result.getById(id)
            .then(data =>{
                setArticulosManufacturado(data);
            })
            .catch(error =>{
                console.log(error);
            })
    }

    const buscarInsumoXDenominacion = async () => {
        const result = new ArticuloInsumoService("http://localhost:8080/articuloInsumo/denominacion");
        const insumosResult = await result.getInsumoByDenominacion(inputValue);
        if (insumosResult) {
            setInsumos(insumosResult);
        } else {
            setInsumos([]);
        }
        
    }
    const agregarInsumo = (insumo:IArticuloInsumo) => {
        const existeInsumo = articuloManufacturado.articuloManufacturadoDetalles.find((insumoDetalle)=> insumoDetalle.articuloInsumo.id === insumo.id)
        if(existeInsumo){
            alert("El insumo ya existe en el arreglo");
        }else{
            const nuevoDetalle: IArticuloManufacturadoDetalles = {
                id: 0,
                cantidad: 0,
                articuloInsumo: insumo
            };
            setArticulosManufacturado(prevState => ({
                ...prevState,
                articuloManufacturadoDetalles: [...prevState.articuloManufacturadoDetalles, nuevoDetalle]
            }));
        }
    };
    
    //La funcion funciona correctamente pero a la hora de eliminar el primer elemento afuera del modal la pagina se recarga y se pierde todo el proceso
    //Se debe revisar o preguntar
    const deleteInsumo = async (articuloInsumo:IArticuloInsumo)=>{
        // console.log(articuloInsumo);
        // console.log(articuloManufacturado)
        // console.log(articuloManufacturado.articuloManufacturadoDetalles)
        if(articuloManufacturado.articuloManufacturadoDetalles){
            let articuloInsumoFilter:IArticuloManufacturadoDetalles[] = articuloManufacturado.articuloManufacturadoDetalles.filter(detalle => detalle.articuloInsumo.id != articuloInsumo.id);
            articuloManufacturado.articuloManufacturadoDetalles = articuloInsumoFilter;
        }
        setArticulosManufacturado(prevState => ( {...prevState,articuloManufacturado}));
        // console.log(articuloManufacturado.articuloManufacturadoDetalles)
    }

    const saveArticulo = () =>{
        console.log(articuloManufacturado)
    }


    useEffect(() => {
        if(Number(id) != 0){
            getArticuloManufacturado("http://localhost:8080/articuloManufacturado", Number(id))
        }
    }, ([]))

    return (
        <div className="container">
            <Link to="/articulos" className="btnVolver">
                <img width={24} height={24} src={arrow_left} alt="arrow_left" />
                <p style={{margin:"0"}}>Volver</p>
            </Link>
            <form action="" className="formContainer">

                <label htmlFor="denominacion">Nombre del producto</label>
                <input type="text" id="denominacion" name="denominacion" defaultValue={articuloManufacturado.denominacion} onChange={(e) => setArticulosManufacturado({ ...articuloManufacturado, denominacion: e.target.value })} />
                <label htmlFor="descripcion">Descripción</label>
                <textarea id="descripcion" name="descripcion" defaultValue={articuloManufacturado.descripcion} onChange={(e) => setArticulosManufacturado({ ...articuloManufacturado, descripcion: e.target.value })}></textarea>
                <label htmlFor="precioVenta">Precio de Venta</label>
                <input type="text" id="precioVenta" name="precioVenta" defaultValue={articuloManufacturado.precioVenta} onChange={(e) => setArticulosManufacturado({ ...articuloManufacturado, precioVenta: e.target.value })} />
                <label htmlFor="tiempoEstimadoMinutos">Tiempo Estimado(minutos)</label>
                <input type="number" id="tiempoEstimadoMinutos" name="tiempoEstimadoMinutos" defaultValue={articuloManufacturado.tiempoEstimadoMinutos} onChange={(e) => setArticulosManufacturado({ ...articuloManufacturado, tiempoEstimadoMinutos: Number(e.target.value) })} />

                <label htmlFor="preparacion">Preparación</label>
                <textarea id="preparacion" name="preparacion" defaultValue={articuloManufacturado.preparacion} onChange={(e) => setArticulosManufacturado({ ...articuloManufacturado, preparacion: e.target.value })}></textarea>
                <label htmlFor="ingrediente">Ingredientes</label>
                {
                    articuloManufacturado.articuloManufacturadoDetalles && articuloManufacturado.articuloManufacturadoDetalles.map((detalle)=>{
                        return(
                            <div style={{display:"flex",flexDirection:"column"}}>
                                <div style={{width:"full",display:"flex",flexDirection:"row",justifyContent:"space-evenly"}} key={detalle.id}>
                                    <input type='number' defaultValue={detalle.cantidad} onChange={e => detalle.cantidad = Number(e.target.value)}/>
                                    <p>{detalle.articuloInsumo?.id}</p>
                                    <p>{detalle.articuloInsumo?.denominacion}</p>
                                    <p>{detalle.articuloInsumo?.precioVenta}</p>
                                    <button style={{ marginBottom:10 }} className="btn btn-danger" onClick={(e) => deleteInsumo(detalle.articuloInsumo)}>Eliminar</button>
                                </div>
                            </div>
                        )
                    })
                }
            </form>
            <Button variant="primary" onClick={handleShow}>
                Añadir Ingrediente
            </Button>
            <button onClick={saveArticulo}>Guardar</button>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                <Modal.Title>Ingredientes</Modal.Title>
                </Modal.Header>
                <Modal.Body style={{display:"flex", flexDirection:"column",gap:"1rem"}}>
                    <div>
                        <input type="text" value={inputValue} onChange={(e) => setInputValue(e.target.value)} />
                        <Button variant="primary" onClick={buscarInsumoXDenominacion}>Mostrar ingredientes</Button>
                        <ul className="listaAgregarInsumo">
                            {
                                insumos.map((insumo, index) => (
                                    <li key={index} onClick={() => agregarInsumo(insumo)}>{insumo.denominacion}</li>
                                ))
                            }
                        </ul>
                    </div>
                    <div>
                        <h3>Ingredientes seleccionados:</h3>
                        <ul className="listaInsumo" style={{}}>
                            {articuloManufacturado.articuloManufacturadoDetalles && articuloManufacturado.articuloManufacturadoDetalles.map((detalle) => (
                                <li key={detalle.articuloInsumo.id}>
                                    {detalle.articuloInsumo?.denominacion}
                                    <svg onClick={(e) => deleteInsumo(detalle.articuloInsumo)} style={{position:"absolute",top:"3px",right:"1px"}} width="14px" height="14px" viewBox="0 0 24.00 24.00" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M7 17L16.8995 7.10051" stroke="#000000" stroke-linecap="round" stroke-linejoin="round"></path> 
                                        <path d="M7 7.00001L16.8995 16.8995" stroke="#000000" stroke-linecap="round" stroke-linejoin="round"></path> 
                                    </svg>
                                </li>
                            ))}
                        </ul>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                <Button variant="primary" onClick={handleClose}>
                    Guardar
                </Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
}