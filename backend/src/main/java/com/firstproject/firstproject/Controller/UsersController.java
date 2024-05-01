package com.firstproject.firstproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.firstproject.firstproject.Model.Users;
import com.firstproject.firstproject.Repository.UsersRepository;



@Controller
public class UsersController {
	
	@Autowired
	private UsersRepository usersRepository;

	@QueryMapping
    public Iterable<Users> allUsers()
    {
    	return usersRepository.findAll();
    }
	
	@QueryMapping
	public Users getUserById(@Argument Long id)
	{
		return usersRepository.findUsersById(id);
	}
}