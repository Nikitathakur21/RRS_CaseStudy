package com.railwayBooking.UserSecurity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.railwayBooking.UserSecurity.model.UserModel;

@Repository   
public interface UserRepository extends MongoRepository<UserModel, String> {
	
	UserModel findByUsername(String username);
}