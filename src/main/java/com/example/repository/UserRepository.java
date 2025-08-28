package com.example.repository;

import com.example.model.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}