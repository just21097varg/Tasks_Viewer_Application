import Login, { Render } from 'react-login-page';
import React, { useState } from 'react';
import DailyStats from './DailyStats';

function Mylogin() {
    const [display,setDisplay] = useState({display: "none"});
    const [dailyStats,setDailyStats] = useState('');
    const [displayLogin,setDisplayLogin] = useState({display: "flex"});
    const [defVal,setDefVal]=useState('password');
    const [user, setUsername] = useState('');
    const [pass, setPassword] = useState('');
    const [userLogged, setUserLogged] = useState('');
    const [otp, setOtp] = useState('');
    const handleUsernameChange = (event) => {
      setUsername(event.target.value);
    };
    const handlePasswordChange = (event) => {
      setPassword(event.target.value);
    };
    const handleTogglePassword = (event) => {
        if(defVal=="password")
            setDefVal('text');
        else
            setDefVal("password");
      };
  
    const handleReset = () => {
      setUsername('');
      setPassword('');
      setDefVal('password');
    };
  
    const handleClick = async () => {
  
      let headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      headers.append('Access-Control-Allow-Origin', 'http://127.0.0.1:8082/login/loginVerify');
  
      let jsonData = {
        "username": user,
        "password": pass
      };
  
      const data = fetch('http://127.0.0.1:8082/login/loginVerify', {
        method: 'POST',
        body: JSON.stringify(jsonData),
        headers: headers
      }).then(response => response.json())
        .then(data => {  
           handleReset();
           console.log("data",data);
          if (data.message === "Login failed") {
            setUserLogged("Invalid Login credentials!!");
          }
          else{
            setUserLogged("  "+user);
            setDisplay({display: "flex"});
            setDisplayLogin({display: "none"});
            setDailyStats(data.data);
            setOtp(data.otp);
            console.log(data.otp);
          }
        })
        .catch(error => console.error(error));  
    };
    return (
      <div className="main">
        <div style={display} className="left">Hello, <bold>{userLogged}</bold></div>
        <div style={display} className="viewStats"><DailyStats value={dailyStats} user={userLogged}></DailyStats></div>
        <div style={displayLogin} className="loginRegister">
          <Login>
            <Render>
              {({ fields, buttons, blocks }) => {
                return (
                  <div>
                    <header>
                      {blocks.title}
                    </header>
                    <div>
                      <label>{fields.username}</label>
                    </div>
                    <div>
                      <label>{fields.password}</label>
                    </div>
                    <div>
                      {buttons.submit}
                      {buttons.reset}
                    </div>
                  </div>
                );
              }}
            </Render>
            <Login.Block keyname="title" tagName="span">
              Login
            </Login.Block>
            
            <Login.Input keyname="username" placeholder="username" value={user} onChange={handleUsernameChange} />
            <Login.Input keyname="password" placeholder="password" value={pass} onChange={handlePasswordChange} type={defVal} onClick={handleTogglePassword} />
            <Login.Button keyname="submit" type="submit" onClick={handleClick}>
              Submit
            </Login.Button>
            <Login.Button keyname="reset" type="reset" onClick={handleReset}>
              Reset
            </Login.Button>
            
          </Login>
        </div>
      </div>);
  };

  export default Mylogin;