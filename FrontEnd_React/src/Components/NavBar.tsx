import { CButton, CCloseButton, CContainer, CDropdown, CDropdownDivider, CDropdownItem, CDropdownMenu, CDropdownToggle, CForm, CFormInput, CNavItem, CNavLink, CNavbar, CNavbarBrand, CNavbarNav, CNavbarToggler, COffcanvas, COffcanvasBody, COffcanvasHeader, COffcanvasTitle } from "@coreui/react"
import { useState } from "react"
import SideBar from "./SideBar"


export default function NavBar() {
    const [visible, setVisible] = useState(false)
    const [sidebarVisible, setSidebarVisible] = useState(false)

    const toggleSidebar = () => {
        setSidebarVisible(!sidebarVisible); 
    };

    return (
        <CNavbar className="bg-primary">
            <CContainer fluid>
                <CNavbarToggler onClick={toggleSidebar} className="me-3" /> 
                <CNavbarBrand href="#">
                    El Buen Sabor
                </CNavbarBrand>
            </CContainer>
        </CNavbar>
    )
}
