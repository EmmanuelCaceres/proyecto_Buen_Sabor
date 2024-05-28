import { SetStateAction, useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import ArticuloManufacturadoService from '../Functions/Services/ArticuloManufacturadoService';
import IArticuloManufacturado from '../Entities/IArticuloManufacturado';
import masObject from '../assets/circle-plus-svgrepo-com.svg';

export default function GrillaArticulo() {
    const [inputValue, setInputValue] = useState('');

    const [articulosManufacturados, setArticulosManufacturados] = useState<IArticuloManufacturado[]>([]);

    const mostrarDatos =(url:string)=>{
        const result = new ArticuloManufacturadoService(url);
        result.getAll()
            .then(data =>{
                setArticulosManufacturados(data);
            })
            .catch(error =>{
                console.log(error)
            })
    }
    const searchItem = (value:string) =>{
        const result = new ArticuloManufacturadoService("http://localhost:8080/articuloManufacturado/name?nombre=")
        result.getArticuloByName(value)
            .then(data =>{
                setArticulosManufacturados(data)
            })
            .catch(error =>{
                console.log(error)
            })
    }

    const handleKeyPress = (event: { key: string; }) => {
        if (event.key === 'Enter') {
            console.log(inputValue)
            searchItem(inputValue);
        }
    };

    const handleDelete = (id:number) => {
        console.log(event);
        const result = new ArticuloManufacturadoService("http://localhost:8080/articuloManufacturado").delete(id);
        window.location.reload;
    }

    const handleInputChange = (event: { target: { value: SetStateAction<string>; }; }) => {
        setInputValue(event.target.value);
    };

    useEffect(() => {
        mostrarDatos("http://localhost:8080/articuloManufacturado")
    }, ([]))

    return (
        <div className="container">
            <div style={{display:"flex",justifyContent:"space-between",alignItems:"center",margin:"0 16px"}}>
                <p style={{fontSize:"32px"}}>Articulos</p>
                <Link to={'save/0'} className='btnPrimary'>
                    <img src={masObject} alt="" />
                    Crear Articulo
                </Link>
            </div>
            <div className='searchGrilla'>
                <input type="text" 
                    onChange={handleInputChange}
                    onKeyPress={handleKeyPress}
                    placeholder="Busca un articulo" />
            </div>
            {
                articulosManufacturados.map((articulo: IArticuloManufacturado) => {
                    return (
                        <div className="itemGrilla" key={articulo.id}>
                            <img width={58} height={58} src={'http://localhost:8080/imagenArticulo/uploads/'+ articulo.imagenes[0].url} alt="" />
                            <p>{articulo.denominacion}</p>
                            <p>{articulo.descripcion}</p>
                            <div>{articulo.precioVenta}</div>
                            <div style={{display:"flex", alignItems:"center"}}>
                                <a style={{marginRight:"1rem"}} href={"articulos/save/" + articulo.id}>
                                    <svg width="24px" height="24px" viewBox="-2.4 -2.4 28.80 28.80" fill="none" xmlns="http://www.w3.org/2000/svg" transform="matrix(1, 0, 0, 1, 0, 0)">
                                        <path fillRule="evenodd" clipRule="evenodd" d="M14.0992 2.39109C15.6207 0.869636 18.0875 0.869636 19.6089 2.39109C21.1304 3.91255 21.1304 6.37932 19.6089 7.90077L12.0149 15.4948C11.5869 15.9228 11.3255 16.1842 11.0342 16.4115C10.691 16.6792 10.3196 16.9087 9.92667 17.096C9.59315 17.2549 9.24244 17.3718 8.66818 17.5632L5.99553 18.4541L5.35386 18.668C4.77951 18.8594 4.14629 18.7099 3.7182 18.2818C3.2901 17.8537 3.14062 17.2205 3.33207 16.6462L4.43683 13.3318C4.62822 12.7576 4.7451 12.4069 4.90405 12.0734C5.09132 11.6804 5.32084 11.309 5.58856 10.9658C5.81578 10.6745 6.07719 10.4131 6.50525 9.98509L14.0992 2.39109ZM5.96092 16.8845L5.11556 16.0391L5.84398 13.8538C6.05606 13.2176 6.14366 12.9589 6.25814 12.7187C6.39854 12.4241 6.57061 12.1457 6.77132 11.8883C6.93497 11.6785 7.12718 11.4845 7.60141 11.0102L13.4924 5.11927C13.7355 5.72927 14.147 6.46549 14.8408 7.15927C15.5345 7.85306 16.2708 8.26447 16.8807 8.50764L10.9898 14.3986C10.5155 14.8728 10.3215 15.065 10.1117 15.2287C9.85436 15.4294 9.57594 15.6015 9.28134 15.7419C9.04113 15.8564 8.78244 15.944 8.14619 16.156L5.96092 16.8845ZM18.076 7.31241C17.9521 7.28522 17.7973 7.24432 17.6213 7.18325C17.1373 7.01532 16.5006 6.69778 15.9014 6.09861C15.3022 5.49944 14.9847 4.86276 14.8168 4.37873C14.7557 4.2027 14.7148 4.04787 14.6876 3.92404L15.1599 3.45175C16.0956 2.51608 17.6126 2.51608 18.5483 3.45175C19.4839 4.38742 19.4839 5.90444 18.5483 6.84011L18.076 7.31241ZM3.25002 22C3.25002 21.5858 3.58581 21.25 4.00002 21.25H20V22.75H4.00002C3.58581 22.75 3.25002 22.4142 3.25002 22Z" fill="#1C274C" /> 
                                    </svg>
                                </a>

                                {/* <div onClick={handleDelete(articulo.id)}>
                                    <svg style={{cursor:"pointer"}} width="24px" height="24px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M4 6H20M16 6L15.7294 5.18807C15.4671 4.40125 15.3359 4.00784 15.0927 3.71698C14.8779 3.46013 14.6021 3.26132 14.2905 3.13878C13.9376 3 13.523 3 12.6936 3H11.3064C10.477 3 10.0624 3 9.70951 3.13878C9.39792 3.26132 9.12208 3.46013 8.90729 3.71698C8.66405 4.00784 8.53292 4.40125 8.27064 5.18807L8 6M18 6V16.2C18 17.8802 18 18.7202 17.673 19.362C17.3854 19.9265 16.9265 20.3854 16.362 20.673C15.7202 21 14.8802 21 13.2 21H10.8C9.11984 21 8.27976 21 7.63803 20.673C7.07354 20.3854 6.6146 19.9265 6.32698 19.362C6 18.7202 6 17.8802 6 16.2V6M14 10V17M10 10V17" stroke="#000000" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"></path> 
                                    </svg>
                                </div> */}
                                <button className="btn btn-danger" onClick={(e) => handleDelete(articulo.id)}>Eliminar</button>
                                
                            </div>

                        </div>
                    )
                })
            }
        </div>
    )
}