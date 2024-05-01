package com.firstproject.firstproject.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

import lombok.Value;

@Service
public class LoginService {
	

    public String verifyLogin(String username, String password) throws SQLException {
    
        String result = "Login failed";
        
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
            	result = "Success";
            }
        }
 
        connection.close();
         
        return result;
    }
}