package com.firstproject.firstproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.firstproject.Service.JwtUtil;


@RestController 
class AuthenticateController {
	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/authenticate")
	private String authenticate() throws Exception{
		
		try {
			authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken("justin", "1234567")
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken("justin");
	}

}
