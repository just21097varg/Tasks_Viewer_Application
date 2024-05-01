package com.firstproject.firstproject.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class RegisterService {
	
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_MOBILE_NUMBER_REGEX = Pattern.compile("^\\d{10}$");

    public String userRegister(String username, String password,String confirmPassword,String email,String mobile) throws SQLException {
    
        String result = "Register success";
        Matcher emailMatcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        Matcher mobileMatcher = VALID_MOBILE_NUMBER_REGEX.matcher(mobile);
        
        if(username.isBlank() || password.isBlank() || confirmPassword.isBlank() || email.isBlank() || mobile.isBlank())
        	result = "Fields are mandatory";  
        
        else if(!emailMatcher.find())
        	result = "Email format invalid";
        
        else if(!mobileMatcher.find())
        	result = "Mobile format invalid";
        
        else if(!password.equals(confirmPassword))
        	result = "Password and ConfirmPassword entered doesn't match";	
        
        else {

        Connection connection = DriverManager.getConnection("jdbc:h2:file:/data/demo", "sa", "password");
		System.out.println("Connected to H2 embedded database.");
		
		String sql = "INSERT INTO USERS(USER_ID,NAME,PASSWORD,EMAIL,MOBILE) VALUES (NEXTVAL('USER_ID_SEQ'),?,?,?,?); ";
		PreparedStatement ps = connection.prepareStatement(sql);
		
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, email);
        ps.setString(4, mobile);
        
        ps.execute();
        
        ps.close();
        connection.close();
        	
        }
         
        return result;
    }
}