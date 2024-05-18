import React, { useState } from 'react';
import Login, { Render } from 'react-login-page';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';


function Register() {
  const defaultTheme = createTheme();
  const [user, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [verifyEmail, setVerifyEmail] = useState('');
  const [mobile, setMobile] = useState('');
  const [pass, setPassword] = useState('');
  const [verifyPass, setVerifyPassword] = useState('');

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };
  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };
  const handleVerifyEmailChange = (event) => {
    setVerifyEmail(event.target.value);
  };
  const handleMobileChange = (event) => {
    setMobile(event.target.value);
  };
  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };
  const handleVerifyPasswordChange = (event) => {
    setVerifyPassword(event.target.value);
  };
  
  const handleReset = () => {
    setUsername('');
    setEmail('');
    setVerifyEmail('');
    setMobile('');
    setPassword('');
    setVerifyPassword('');
  };
  const handleClick = async () => {

    if(email===verifyEmail){

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    headers.append('Access-Control-Allow-Origin', 'http://127.0.0.1:8082/register/addUser');

    var jsonData = {
      "username": user,
      "password": pass,
      "confirmpassword":verifyPass,
      "email":email,
      "mobile":mobile
    };

    const data = fetch('http://127.0.0.1:8082/register/addUser', {
      method: 'POST',
      body: JSON.stringify(jsonData),
      headers: headers
    }).then(response => response.json())
      .then(data => 
        { handleReset();
          if (data.message === "Register success") 
            alert('Successfully Registered!'); 
          else 
            alert(data.message);
        })
      .catch(error => console.error(error));
    }
    else{
      alert('Email IDs not matching'); 
    }
  };
  return (<div className="loginRegister">
     <Login>
      <Render>
        {({ fields, buttons, blocks, $$index }) => {
          return (
            <div id="form">
              <header>
                {blocks.title}
              </header>
              <div>
                Username : <label>{fields.username}</label>
              </div>
              <div>
                Email : <label>{fields.email}</label>
              </div>
              <div>
                Verify Email : <label>{fields.verifyEmail}</label>
              </div>
              <div>
                Mobile : <label>{fields.mobile}</label>
              </div>
              <div>
                Password : <label>{fields.password}</label>
              </div>
              <div>
                Confirm Password : <label>{fields.verifyPassword}</label>
              </div>
              {buttons.showpassword}
              <div>
                {buttons.submit}
                {buttons.reset}
              </div>
            </div>
          );
        }}
      </Render>
      <Login.Block keyname="title" tagName="span">
        Register
      </Login.Block>
      <Login.Input keyname="username" placeholder="Please input Username" value={user} onChange={handleUsernameChange} />
      <Login.Input keyname="email" placeholder="please enter email ID" value={email} onChange={handleEmailChange} />
      <Login.Input keyname="verifyEmail" placeholder="please re-enter emailID" value={verifyEmail} onChange={handleVerifyEmailChange} />
      <Login.Input keyname="mobile" placeholder="please enter mobile number" value={mobile} onChange={handleMobileChange} />
      <Login.Input keyname="password" placeholder="please enter password" value={pass} onChange={handlePasswordChange} />
      <Login.Input keyname="verifyPassword" placeholder="please re-enter password" value={verifyPass} onChange={handleVerifyPasswordChange} />
      <Button keyname="submit" type="submit" onClick={handleClick}>
        Submit
      </Button>
      <Button keyname="reset" type="reset" onClick={handleReset}>
        Reset
      </Button>
    </Login> 

    {/* <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <Box component="form" noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoComplete="email"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
            />
            <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Remember me"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item xs>
                <Link href="#" variant="body2">
                  Forgot password?
                </Link>
              </Grid>
              <Grid item>
                <Link href="#" variant="body2">
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider> */}
    </div> 
  );
};
export default Register;