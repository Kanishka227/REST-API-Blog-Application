package com.blog_application.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.blog_application.entities.User;


public interface UserRepository extends JpaRepository<User,Integer>{

	
	
	

}
