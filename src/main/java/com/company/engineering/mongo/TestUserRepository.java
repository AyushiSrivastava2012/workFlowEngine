package com.company.engineering.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.company.engineering.pojo.TestUser;

@Repository
public interface TestUserRepository extends MongoRepository<TestUser,String>{

}
