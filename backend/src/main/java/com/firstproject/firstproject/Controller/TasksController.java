package com.firstproject.firstproject.Controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.firstproject.DAO.LoginDAO;
import com.firstproject.firstproject.DAO.TaskDAO;
import com.firstproject.firstproject.Service.TaskService;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TasksController {
	
	@Autowired
	TaskService taskService;

	@PostMapping("/userId")
    @ResponseBody
    public Map<String,Object> fetchTaskForUser(@RequestBody LoginDAO loginDAO) throws SQLException{
      
        List<TaskDAO> result = taskService.getTasks(loginDAO.getUsername());
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("message", result);
        return response;
    }
	
	@PostMapping("/addTask")
    @ResponseBody
    public Map<String,Object> addTaskForUser(@RequestBody TaskDAO taskDAO) throws SQLException{
      
        taskService.addTask(taskDAO.getUsername() , taskDAO.getTaskDesc());
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        return response;
    }
	
}
