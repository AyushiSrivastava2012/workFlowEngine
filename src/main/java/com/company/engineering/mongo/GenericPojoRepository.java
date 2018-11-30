package com.company.engineering.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.company.engineering.pojo.GenericPojo;
import com.company.engineering.pojo.State;

@Repository
public interface GenericPojoRepository extends MongoRepository<GenericPojo,String>{

	List<GenericPojo> findByDocIdAndState(String docId, State state);

}
