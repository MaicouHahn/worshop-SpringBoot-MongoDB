package com.nelioalvez.worshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalvez.worshopmongo.domain.Post;
import com.nelioalvez.worshopmongo.repository.PostRepository;
import com.nelioalvez.worshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService  {
	
	@Autowired //injecao de dependencia automatica do springboot
	private PostRepository repo;
	

	public Post findById(String id) {
		
		Optional<Post> obj = repo.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
		
	}
	

	
}
