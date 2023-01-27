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
	
	public void delete(String id) {
		findById(id);//confere se o ID existe caso nao exista ele ja lança a excessao
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());//cria um objeto copia que recebe as informaçoes do User relacionado ao ID que foi passado
		updateData(newObj,obj);//nesse metodo ele altera o objeto copiado com as informaçoes passadas no endpoint
		
		return repo.save(newObj);//e aqui faz o update
	}
	
	private void updateData(User newObj, User obj) {
		
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
	}
	
}
