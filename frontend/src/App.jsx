import React, { useState } from 'react';
import "./App.css";
import logo from './assets/Logo.jpg';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavBar from './components/NavBar';
import {BrowserRouter} from 'react-router-dom';

function Logo(){
  return(<img src={logo}/>);
}

function App() {
    return (
      <div className="wholePage">
        {/* <div className="logo"><Logo/></div>  */}
        <BrowserRouter>
            <NavBar />
        </BrowserRouter>
       
       
      </div>
      
    );
};

export default App;