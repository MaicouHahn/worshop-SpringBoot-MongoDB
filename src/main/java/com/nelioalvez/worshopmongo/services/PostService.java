package com.nelioalvez.worshopmongo.services;

import java.util.Date;
import java.util.List;
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
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	public List<Post> fullSearch(String text, Date minDate,Date maxDate){
		maxDate = new Date(maxDate.getTime()+24*60*60*1000);
		return repo.fullsearch(text, minDate, maxDate);
	}
	
}
