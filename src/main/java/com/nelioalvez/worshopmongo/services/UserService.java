package com.nelioalvez.worshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalvez.worshopmongo.domain.User;
import com.nelioalvez.worshopmongo.repository.UserRepository;

@Service
public class UserService  {
	
	@Autowired //injecao de dependencia automatica do springboot
	private UserRepository repo;
	
	public List<User>findAll(){
		return repo.findAll();
	}
}
