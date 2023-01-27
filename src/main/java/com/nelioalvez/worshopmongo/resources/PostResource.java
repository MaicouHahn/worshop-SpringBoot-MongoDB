package com.nelioalvez.worshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalvez.worshopmongo.domain.Post;
import com.nelioalvez.worshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET) //indica o metodo Http Get
	public ResponseEntity <Post> findById(@PathVariable String id){ //relaciona o ID passado na URL com o id que o metodo recebera
		
		Post obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}

	
}
