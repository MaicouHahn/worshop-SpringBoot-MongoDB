package com.nelioalvez.worshopmongo.config;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nelioalvez.worshopmongo.domain.Post;
import com.nelioalvez.worshopmongo.domain.User;
import com.nelioalvez.worshopmongo.dto.AuthorDTO;
import com.nelioalvez.worshopmongo.repository.PostRepository;
import com.nelioalvez.worshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		postRepository.deleteAll();
		userRepository.deleteAll();//para limpar o mongoDB
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));//é salvo os usuarios antes para que tenham um ID proprio e depois é feito a copia do Dto abaixo
		
		Post post1 = new Post(null,sdf.parse("21/03/2018"),"Partiu viajem","Vou viajar para São Paulo. Abraços",new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("23/03/2018"),"Bom dia","Acordei feliz hoje!",new AuthorDTO(maria));
		
		
		postRepository.saveAll(Arrays.asList(post1,post2));
	}

}
