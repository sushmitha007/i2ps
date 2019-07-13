package com.stackroute.intelligentservice.repository;

import com.stackroute.intelligentservice.domain.IntelligentService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface IntelligentServiceRepository extends MongoRepository<IntelligentService,String> {


    public IntelligentService findByRole(String role);  //repo method to find data by role
//    public IntelligentService exitsByRole(String role);



}
