package com.firstproject.firstproject.Controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.firstproject.DAO.RegisterDAO;
import com.firstproject.firstproject.Service.LoginService;
import com.firstproject.firstproject.Service.RegisterService;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	RegisterService registerService;

    @PostMapping("/addUser")
    @ResponseBody
    public Map<String,Object> userRegister(@RequestBody RegisterDAO registerDAO) throws SQLException{
      
        String result = registerService.userRegister(registerDAO.getUsername(),registerDAO.getPassword(),registerDAO.getConfirmpassword(),registerDAO.getEmail(),registerDAO.getMobile());
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("message", result);
        return response;
    }
}