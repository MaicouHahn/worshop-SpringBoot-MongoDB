package com.nelioalvez.worshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalvez.worshopmongo.domain.User;
import com.nelioalvez.worshopmongo.dto.UserDTO;
import com.nelioalvez.worshopmongo.repository.UserRepository;
import com.nelioalvez.worshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService  {
	
	@Autowired //injecao de dependencia automatica do springboot
	private UserRepository repo;
	
	public List<User>findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
		
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
	}
}
