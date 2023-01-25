package com.nelioalvez.worshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nelioalvez.worshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
		
	
}
