package com.firstproject.firstproject.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.firstproject.DAO.LoginDAO;
import com.firstproject.firstproject.DAO.TaskDAO;
import com.firstproject.firstproject.Service.LoginService;
import com.firstproject.firstproject.Service.TaskService;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	TaskService taskService;

    @PostMapping("/loginVerify")
    @ResponseBody
    public Map<String,Object> loginVerify(@RequestBody LoginDAO loginDAO) throws SQLException{
      
        String result = loginService.verifyLogin(loginDAO.getUsername(),loginDAO.getPassword());
        List<TaskDAO> list=new ArrayList<>();
        if(result.equals("Success")){
        	list=taskService.getTasks(loginDAO.getUsername());
        }
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("message", result);
        response.put("data", list);
        return response;
    }
}