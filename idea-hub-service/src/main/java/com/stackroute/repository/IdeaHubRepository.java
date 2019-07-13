package com.stackroute.repository;

import com.stackroute.domain.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


public interface IdeaHubRepository extends MongoRepository<Idea,String> {
        @Override
        boolean existsById(String s);
        Idea findByTitle(String title);
        public List<Idea> findByEmailId(String emailId);

}

