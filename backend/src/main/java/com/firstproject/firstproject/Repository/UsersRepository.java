package com.firstproject.firstproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.firstproject.firstproject.Model.Users;

@Component
public interface UsersRepository extends JpaRepository<Users, Long>  {

	Users findUsersById(Long id);
}