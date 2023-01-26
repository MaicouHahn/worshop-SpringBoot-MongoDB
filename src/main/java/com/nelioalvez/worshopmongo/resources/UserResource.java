package com.nelioalvez.worshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalvez.worshopmongo.domain.User;
import com.nelioalvez.worshopmongo.dto.UserDTO;
import com.nelioalvez.worshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")

public class UserResource {
	
	@Autowired
	private UserService service;
	
	//@GetMapping //esse comando identifica que é um REST GET
	@RequestMapping(method=RequestMethod.GET) //esse codigo faz a mesma coisa que a de cima
	public ResponseEntity <List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();//lista recebe o resultado do serviço findall
		List<UserDTO> listDto = list.stream().map( x->new UserDTO(x) ).collect(Collectors.toList()); //converte os objetos User em UserDTO
		
		return ResponseEntity.ok().body(listDto);//passado a lista como resposta da requiziçao
	}

}
