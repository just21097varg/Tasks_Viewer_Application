package com.firstproject.firstproject.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.firstproject.firstproject.DAO.TaskDAO;

@Service
public class TaskService {
	

    public List<TaskDAO> getTasks(String username) throws SQLException {
    
        List<TaskDAO> result = new ArrayList<>();
        TaskDAO data;
        
        Connection connection = DriverManager.getConnection("jdbc:h2:file:/data/demo", "sa", "password");
		System.out.println("Connected to H2 embedded database.");
		
		String sql = "SELECT * FROM TASKS T LEFT JOIN USERS U WHERE T.USER_ID=U.USER_ID"
				+ " AND U.NAME = '"+username+"'";
		 
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next()) {
        	
            Long taskId = resultSet.getLong("TASK_ID");
            String taskDesc = resultSet.getString("TASK_DESC");
            String status = resultSet.getString("STATUS");
            
            data= new TaskDAO();
            data.setTaskID(taskId);
            data.setTaskDesc(taskDesc);
            data.setStatus(status);
            
            
            result.add(data);
        }
 
        connection.close();
         
        return result;
    }
    
    public List<TaskDAO> addTask(String username,String taskDesc) throws SQLException {
        
        TaskDAO data;
        Long userId = null;
        
        Connection connection = DriverManager.getConnection("jdbc:h2:file:/data/demo", "sa", "password");
		System.out.println("Connected to H2 embedded database.");
		
		String sql = "SELECT USER_ID FROM USERS WHERE NAME = '"+username+"'";
		PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        
        if(resultSet.next()) {
        	
            userId = resultSet.getLong("USER_ID");
        }
		
		
		sql = "INSERT INTO TASKS(TASK_ID,TASK_DESC,USER_ID,STATUS) VALUES (NEXTVAL('TASK_ID_SEQ'),?,?,'IN PROCESS')";
		 
        statement = connection.prepareStatement(sql);
        statement.setString(1, taskDesc);
        statement.setLong(2, userId);
        statement.executeUpdate();
 
        connection.close();
         
        return null;
    }
}