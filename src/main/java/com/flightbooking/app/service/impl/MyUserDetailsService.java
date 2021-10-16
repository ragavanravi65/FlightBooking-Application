package com.flightbooking.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService{
	
	@Value("#{'${my.userdetails.list}'.split(',')}") 
	List<String> usernames;
	//4
	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		if(usernames.contains(user))
		return new User(user,"$2a$12$ako0LouBlRClFfx9WKGC3.UyO78bj1HWQVYz8dqFS/j4HPyF9YFhC",new ArrayList<>());
		else
			throw new UsernameNotFoundException("Invalid User Details: "+ user);
	}

}
