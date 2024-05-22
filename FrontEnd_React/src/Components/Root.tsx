import { Outlet } from "react-router-dom"
import SideBar from "./SideBar"
import NavBar from "./NavBar"

export default function Root(){
    return(
      <div /*style={{ display: "flex", flexDirection: "column", height: "100vh" }}*/>
            <NavBar />
            <div style={{ display: "flex", flex: 1 }}>
                <SideBar />
                <div /*style={{ flex: 1, overflowY: "auto" }}*/>
                    <Outlet />
                </div>
            </div>
      </div>
    )
}