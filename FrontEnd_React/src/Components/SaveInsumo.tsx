import { useEffect, useState } from "react";
import { useParams,useNavigate } from "react-router-dom"
import { Link } from "react-router-dom";
import IArticuloInsumo from "../Entities/IArticuloInsumo";
import arrow_left from "../assets/arrow-circle-left-svgrepo-com.svg";
import ArticuloInsumoService from "../Functions/Services/ArticuloInsumoService";
//import IImagen from "../Entities/IImagenArticulo";
import ICategoria from "../Entities/ICategoria";
import CatetgoriaService from "../Functions/Services/CategoriaService";
import IUnidadMedida from "../Entities/IUnidadMedida";
import UnidadMedidaService from "../Functions/Services/UnidadMedidaService";
import ImagenArticuloService from "../Functions/Services/ImagenArticuloService";

export default function SaveInsumo() {
    const { id } = useParams();
    const navigate = useNavigate();
    // const [imgUrl, setImgUrl] = useState<IImagen>(
    //     {
    //         id: 0,
    //         url: ''
    //     }
    // );
    const [categoria, setCategoria] = useState<ICategoria[]>([])
    const [unidadMedida, setUnidadMedida] = useState<IUnidadMedida[]>([]);

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);

    const onFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const formData = new FormData();
        const file = event.target.files?.[0];
    
        if (!file) {
            console.log("No file selected");
            return;
        }
    
        formData.append("file", file);
        console.log(formData);
    
        const result = new ImagenArticuloService("http://localhost:8080/imagenArticulo");
        result.postImagen(formData)
            .then(data => {
                if (data) {
                    console.log(data);
                    setArticulosInsumo(prevState => ({
                        ...prevState,
                        imagenes: [
                            ...prevState.imagenes,
                            {
                                id: data.id,
                                url: data.url
                            }
                        ]
                    }));
                } else {
                    console.log("No data received");
                }
            })
            .catch(error => {
                console.log(error);
            });
    };

    const [articuloInsumo, setArticulosInsumo] = useState<IArticuloInsumo>(
        {
            id: Number(id),
            denominacion: '',
            precioVenta: '',
            unidadMedida: {
                id: 0,
                denominacion: '',
            },
            imagenes: [],
            categoria: {
                id: 0,
                denominacion: '',
                sucursales: []
            },
            precioCompra: 0,
            stockActual: 0,
            stockMaximo: 0,
            esParaElaborar: true,
        }
    );

    const getArticuloInsumo = async (baseUrl: string, id: number) => {
        const result = new ArticuloInsumoService(baseUrl);
        await result.getById(id)
            .then(data => {
                if (data !== null) {
                    setArticulosInsumo(data);
                } else {
                    console.log("El insumo no se encontró.");
                }
            })
            .catch(error => {
                console.log(error);
            })
    }

    // const buscarInsumoXDenominacion = async () => {
    //     const result = new ArticuloInsumoService("http://localhost:8080/articuloInsumo/search?denominacion=");
    //     const insumosResult = await result.getInsumoByDenominacion(inputValue);
    //     if (insumosResult) {
    //         setInsumos(insumosResult);
    //     } else {
    //         setInsumos([]);
    //     }
    // }


    const getAllCategories = async () => {
        const result = new CatetgoriaService("http://localhost:8080/categoria")
        const categoriaResult = await result.getAll();
        setCategoria(categoriaResult);
    }

    const getAllUnidad = async () => {
        const result = new UnidadMedidaService("http://localhost:8080/unidadMedida")
        const unidadMedidaResult = await result.getAll();
        setUnidadMedida(unidadMedidaResult)
    }

    // const agregarInsumo = (insumo: IArticuloInsumo) => {
    //     const existeInsumo = articuloManufacturado.articuloManufacturadoDetalles.find((insumoDetalle) => insumoDetalle.articuloInsumo.id === insumo.id)
    //     if (existeInsumo) {
    //         alert("El insumo ya existe en el arreglo");
    //     } else {
    //         const nuevoDetalle: IArticuloManufacturadoDetalles = {
    //             id: 0,
    //             cantidad: 0,
    //             articuloInsumo: insumo
    //         };
    //         setArticulosManufacturado(prevState => ({
    //             ...prevState,
    //             articuloManufacturadoDetalles: [...prevState.articuloManufacturadoDetalles, nuevoDetalle]
    //         }));
    //     }
    // };


    
    //La funcion funciona correctamente pero a la hora de eliminar el primer elemento afuera del modal la pagina se recarga y se pierde todo el proceso
    //Se debe revisar o preguntar
    // const deleteInsumo = async (articuloInsumo: IArticuloInsumo) => {
    //     let articuloInsumoFilter: IArticuloManufacturadoDetalles[] = [];
    //     if (articuloManufacturado.articuloManufacturadoDetalles) {
    //         articuloInsumoFilter = articuloManufacturado.articuloManufacturadoDetalles.filter(detalle => detalle.articuloInsumo.id !== articuloInsumo.id);
    //     }
    //     setArticulosManufacturado(prevState => ({ ...prevState, articuloManufacturadoDetalles: articuloInsumoFilter }));
    // }

    const handleChangeCategorySelect = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const categoriaSeleccionadaId = parseInt(e.target.value); // Convertir a número
        const categoriaSeleccionada = categoria.find(c => c.id === categoriaSeleccionadaId);
        if (categoriaSeleccionada) {
            setArticulosInsumo(prevState => ({
                ...prevState, // Mantiene las demás propiedades
                categoria: categoriaSeleccionada // Actualiza solo la categoría
            }));
        }
    }


    const handleChangeUnidadSelect = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const unidadSeleccionadaId = parseInt(e.target.value); // Convertir a número
        const unidadSeleccionada = unidadMedida.find(c => c.id === unidadSeleccionadaId);
        if (unidadSeleccionada) {
            setArticulosInsumo(prevState => ({
                ...prevState, // Mantiene las demás propiedades
                unidadMedida: unidadSeleccionada // Actualiza solo la unidad de medida
            }));
        }
    }

    const saveArticulo = async () => {
        console.log(articuloInsumo);
        if (Number(id) !== 0) {
            await new ArticuloInsumoService("http://localhost:8080/articuloInsumo").put(Number(id), articuloInsumo);
        } else {
            await new ArticuloInsumoService("http://localhost:8080/articuloInsumo").post(articuloInsumo);
        }
        alert("Insumo guardado con exito!");
        handleClose();
        navigate(-1);
    };
    // const handleFileChange = (e)=>{
    //     const selectedFile = e.target.files[0];
    //     const imageUrl = selectedFile.name; // Suponiendo que aquí guardas la URL de la imagen

    //     setImgUrl({
    //     id: Number(id) !== 0 ? articuloManufacturado.imagenes[0].id : 0,
    //     url: imageUrl
    //     });
    // }
    const handleRadioChange = (e: { target: { value: string; }; }) => {
        setArticulosInsumo({ 
            ...articuloInsumo, 
          esParaElaborar: e.target.value === 'true' // Convertir a booleano
        });
    };


    useEffect(() => {
        console.log(id)
        getAllCategories()
        getAllUnidad()
        if (Number(id) != 0) {
            getArticuloInsumo("http://localhost:8080/articuloInsumo", Number(id))

        }
        // console.log(articuloManufacturado.articuloManufacturadoDetalles)
        console.log(categoria)
    }, ([]))

    return (
        <div className="container">
            <Link to="/insumos" className="btnVolver">
                <img width={24} height={24} src={arrow_left} alt="arrow_left" />
                <p style={{ margin: "0" }}>Volver</p>
            </Link>
            <form action="" className="formContainer">
                <label htmlFor="denominacion">Nombre del insumo</label>
                <input type="text" id="denominacion" name="denominacion" defaultValue={articuloInsumo.denominacion} onChange={(e) => setArticulosInsumo({ ...articuloInsumo, denominacion: e.target.value })} />
                <label htmlFor="precioCompra">Precio de compra </label>
                <input id="precioCompra" name="precioCompra" defaultValue={Number(articuloInsumo.precioCompra)} onChange={(e) => setArticulosInsumo({ ...articuloInsumo, precioCompra: Number(e.target.value) })}></input>
                <label htmlFor="stockActual">Stock actual</label>
                <input type="text" id="stockActual" name="stockActual" defaultValue={Number(articuloInsumo.stockActual)} onChange={(e) => setArticulosInsumo({ ...articuloInsumo, stockActual: Number(e.target.value) })} />
                <label htmlFor="stockMaximo">Stock maximo</label>
                <input type="number" id="stockMaximo" name="stockMaximo" defaultValue={Number(articuloInsumo.stockMaximo)} onChange={(e) => setArticulosInsumo({ ...articuloInsumo, stockMaximo: Number(e.target.value) })} />
                <input type="file" onChange={onFileChange} />
                <div style={{ display: "flex", justifyContent: "start",gap:"3rem",margin:"1rem 0" }}>
                    <div>
                        <label htmlFor="categoria" style={{marginRight:"1rem"}}>Categorias</label>
                        <select name="categoria" value={articuloInsumo.categoria.id} onChange={handleChangeCategorySelect}>
                            {
                                categoria.map((categoria: ICategoria) => {
                                    return (
                                        <option key={categoria.id} value={categoria.id}>{categoria.denominacion}</option>
                                    )
                                })
                            }
                        </select>
                    </div>
                    <div>

                        <label htmlFor="unidades" style={{marginRight:"1rem"}}>Unidades</label>
                        <select name="unidades" value={articuloInsumo.unidadMedida.id} onChange={handleChangeUnidadSelect}>
                            {
                                unidadMedida.map((unidad: IUnidadMedida) => {
                                    return (
                                        <option key={unidad.id} value={unidad.id}>{unidad.denominacion}</option>
                                    )
                                })
                            }
                        </select>
                    </div>
                </div>
                {/* <label htmlFor="imagen">Imagen</label>
                <input type="file" name="imagen" id="imagen" onChange={handleFileChange}/> */}
                <label htmlFor="esParaElaborar">Es un insumo para Elaborar? </label>
                {/* <input type="radio" id="esParaElaborar" name="esParaElaborar" defaultValue={articuloInsumo.esParaElaborar} onChange={(e) => setArticulosInsumo({ ...articuloInsumo, esParaElaborar: e.target.value })}></input>
                <input type="radio" id="esParaElaborar2" name="esParaElaborar" defaultValue={articuloInsumo.esParaElaborar} onChange={(e) => setArticulosInsumo({ ...articuloInsumo, esParaElaborar: e.target.value })}></input> */}
                <label>
                    <input
                    type="radio"
                    id="esParaElaborarSi"
                    name="esParaElaborar"
                    value="true"
                    checked={articuloInsumo.esParaElaborar === true}
                    onChange={handleRadioChange}
                    />
                    Sí
                </label>
                <label>
                    <input
                    type="radio"
                    id="esParaElaborarNo"
                    name="esParaElaborar"
                    value="false"
                    checked={articuloInsumo.esParaElaborar === false}
                    onChange={handleRadioChange}
                    />
                    No
                </label>

            </form>

            <button className="btn btn-primary" onClick={saveArticulo}>Guardar</button>
        </div>
    )
}
