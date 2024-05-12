import { Outlet } from "react-router-dom"
import NavBar from "./NavBar"

export default function Root(){
    return(
        <div style={{display:"flex",flexDirection:"row",height:"100%"}}>
          <NavBar/>
          <Outlet />
        </div>
    )
}