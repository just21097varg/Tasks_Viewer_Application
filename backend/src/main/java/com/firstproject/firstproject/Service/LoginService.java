package com.firstproject.firstproject.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Value;

@Service
public class LoginService {
	
	@Autowired
	EmailService emailService;
	
    public List<String> verifyLogin(String username, String password) throws SQLException {
    
    	List<String> result = new ArrayList<>();
        String mess = "Login failed";
        
        Connection connection = DriverManager.getConnection("jdbc:h2:file:/data/demo", "sa", "password");
		System.out.println("Connected to H2 embedded database.");
		
		String sql = "SELECT * FROM USERS";
		 
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next()) {
        	
            String name = resultSet.getString("NAME");
            String pass = resultSet.getString("PASSWORD");
            if(username.equals(name) && pass.equals(password))
            {
            	mess = "Success";
            	String otp = emailService.sendEmail(resultSet.getString("EMAIL"));
            	result.add(mess);
            	result.add(otp);
            }
        }
 
        connection.close();
         
        return result;
    }
}