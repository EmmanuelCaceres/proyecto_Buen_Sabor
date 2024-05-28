import { useEffect, useState } from "react";
import { useParams,useNavigate } from "react-router-dom"
import { Link } from "react-router-dom";
import arrow_left from "../assets/arrow-circle-left-svgrepo-com.svg";
import ICategoria from "../Entities/ICategoria";
import CategoriaService from "../Functions/Services/CategoriaService";

export default function SaveCategoria() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const [categoria, setCategoria] = useState<ICategoria>({
        id: Number(id),
        denominacion: '',
        sucursales: [],
        subCategorias: [],
        articulos: []
    });

    const onFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const formData = new FormData();
        const file = event.target.files?.[0];
    
        if (!file) {
            console.log("No file selected");
            return;
        }
    
        formData.append("file", file);
        console.log(formData);
    }
    
    //     const result = new ImagenArticuloService("http://localhost:8080/imagenArticulos");
    //     result.postImagen(formData)
    //         .then(data => {
    //             if (data) {
    //                 setArticulosInsumo(prevState => ({
    //                     ...prevState,
    //                     imagenes: [
    //                         ...prevState.imagenes,
    //                         {
    //                             id: data.id,
    //                             url: data.url
    //                         }
    //                     ]
    //                 }));
    //             } else {
    //                 console.log("No data received");
    //             }
    //         })
    //         .catch(error => {
    //             console.log(error);
    //         });
    // };

    const getCategoryByDenominacion = async (baseUrl: string, id: number) => {
        const result = new CategoriaService(baseUrl);
        await result.getById(id)
            .then(data => {
                if (data !== null) {
                    setCategoria(data);
                    console.log("DATA: " + JSON.stringify(data, null, 2));
                } else {
                    console.log("La categoria no se encontró.");
                }
            })
            .catch(error => {
                console.log(error);
            })
    }

    const SaveCategoria = async () => {
        if (Number(id) !== 0) {
            await new CategoriaService("http://localhost:8080/categorias").put(Number(id), categoria);
        } else {
            await new CategoriaService("http://localhost:8080/categorias").post(categoria);
        }
        alert("Categoria guardada con exito!");
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


    useEffect(() => {
        if (Number(id) !== 0) {
            getCategoryByDenominacion("http://localhost:8080/categorias", Number(id))
        }
    }, [id])

    return (
        <div className="container">
            <Link to="/categorias" className="btnVolver">
                <img width={24} height={24} src={arrow_left} alt="arrow_left" />
                <p style={{ margin: "0" }}>Volver</p>
            </Link>
            <form action="" className="formContainer">
                <label htmlFor="denominacion">Nombre de la categoria</label>
                <input type="text" id="denominacion" name="denominacion" value={categoria.denominacion} onChange={(e) => setCategoria({ ...categoria, denominacion: e.target.value })} />
                {/*<label htmlFor="precioCompra">Precio de compra </label>
                <input type="number" id="precioCompra" name="precioCompra" value={articuloInsumo.precioCompra} onChange={(e) => setArticulosInsumo({ ...articuloInsumo, precioCompra: Number(e.target.value) })}></input>
                <label htmlFor="stockActual">Stock actual</label>
                <input type="text" id="stockActual" name="stockActual" value={Number(articuloInsumo.stockActual)} onChange={(e) => setArticulosInsumo({ ...articuloInsumo, stockActual: Number(e.target.value) })} />
                <label htmlFor="stockMaximo">Stock maximo</label>
                <input type="number" id="stockMaximo" name="stockMaximo" value={Number(articuloInsumo.stockMaximo)} onChange={(e) => setArticulosInsumo({ ...articuloInsumo, stockMaximo: Number(e.target.value) })} />
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
                <label htmlFor="esParaElaborar">Es un insumo para Elaborar? </label>
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

                {articuloInsumo.esParaElaborar === false && (
                    <>
                        <label htmlFor="precioVenta">Precio de Venta</label>
                        <input 
                            type="number" 
                            id="precioVenta" 
                            name="precioVenta" 
                            value={articuloInsumo.precioVenta} 
                            onChange={(e) => setArticulosInsumo({ ...articuloInsumo, precioVenta: Number(e.target.value) })} 
                        />
                    </>
                )} */}
            </form>

            <button className="btn btn-primary" onClick={SaveCategoria}>Guardar</button>
        </div>
        )
}

