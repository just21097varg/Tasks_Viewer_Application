package com.firstproject.firstproject.Service;

import java.util.Date;
import java.util.HashMap;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	private String secret = "justtest-token";
	
	public String generateToken(String user) {
		return Jwts.builder().setClaims(new HashMap<>()).setSubject(user).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
	}

}
