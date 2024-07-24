import Register from './Register';
import Login from './Login';
import Home from './Home'
import About from './About'
import Contact from './Contact'
import { Route, Routes, Link } from 'react-router-dom';
import { FaBars } from "react-icons/fa";

function NavBar() {

    let navigateList = [
        { name: 'Home', path: '/home', element: <Home /> },
        { name: 'About', path: '/about', element: <About /> },
        { name: 'Login', path: '/login', element: <Login /> },
        { name: 'Register', path: '/register', element: <Register /> },
        { name: 'Contact', path: '/contact', element: <Contact /> },
    ];

    return <>
        <div className="navigate">

            {navigateList.map(item => <Link to={item['path']} className="navMenu"><strong> {item['name']}</strong></Link>)}

            <a href="#" className="icon" >
                <FaBars />
            </a>
        </div>
        <Routes>
            {navigateList.map(item => <Route path={item['path']} element={item['element']}></Route>)}
        </Routes>
    </>

}

export default NavBar;