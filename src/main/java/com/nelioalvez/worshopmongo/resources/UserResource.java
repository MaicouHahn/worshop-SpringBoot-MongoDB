package com.nelioalvez.worshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET) //indica o metodo Http Get
	public ResponseEntity <UserDTO> findById(@PathVariable String id){ //relaciona o ID passado na URL com o id que o metodo recebera
		
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping( method=RequestMethod.POST) //indica o metodo Http Post(Insert)
	//ou @PostMapping
	public ResponseEntity <Void> insert(@RequestBody UserDTO objDto){ //@RequestBody para permitir que ele recebe um objeto no endpoint
		
		User obj = service.fromDTO(objDto); //cria um User atraves da conversao do UserDto passado no parametro
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		//retorna o endereco do elemento criado no exemplo foi http://localhost:8080/users/63d3ffd36dd54151c39b2d1d onde o caminho leva ao User criado
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE) //indica o metodo Http Delete
	public ResponseEntity <Void> deleteById(@PathVariable String id){ //relaciona o ID passado na URL com o id que o metodo recebera
		
		service.delete(id);
		
		return ResponseEntity.noContent().build(); //o no content é usado pois o retorno é vazio nesse metodo
	}
}
