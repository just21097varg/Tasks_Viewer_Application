import React, { useState } from 'react';
import "./App.css";
import Register from './components/Register';
import Login from './components/Login';
import logo from './assets/Logo.png';
import {Route,Routes,Link} from 'react-router-dom';
import ReactPlayer from 'react-player';
import vid from './assets/WMS_v1.1.0_Inventory Move.mp4';
import { FaBars,FaHome } from "react-icons/fa";
import { BsInfoLg } from "react-icons/bs";
function Logo(){
  return(<img src={logo}/>);
}

function App() {
    return (
      <div className="wholePage">
        {/* <div className="logo"><Logo/></div>  */}
       <div className="navigate">
       
         <Link to="/home" className="navMenu"><FaHome/><strong> Home</strong></Link>
         <Link to="/about" className="navMenu"><BsInfoLg/><strong>About</strong></Link>
         <Link to="/login" className="navMenu"><strong>Login</strong></Link>
         <Link to="/register"  className="navMenu"><strong>Register</strong></Link>
         <a href="#" class="icon" onclick="myFunction()">
            <FaBars/>
  </a>
  </div>
      <Routes>
         <Route path="/login" element={<Login/>}></Route>
         <Route path="/register" element={<Register/>}></Route>
      </Routes>
       
      </div>
      
    );
};

export default App;