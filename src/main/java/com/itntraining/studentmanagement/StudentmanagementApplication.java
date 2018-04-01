package com.itntraining.studentmanagement;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@SpringBootApplication
public class StudentmanagementApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StudentmanagementApplication.class, args);
	}
	@Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception{
        System.out.println("hello ");
        if(repo.count()==0){
      
           repo.save(new User( "admin", "admin", Arrays.asList(new Role("USER"),new Role("Actuator")) ));
           System.out.println("saved");
       }
        builder.userDetailsService((username)-> {
            return new CustomUserDetailsService(repo.findByUsername(username));
        }
        );
    }
}
