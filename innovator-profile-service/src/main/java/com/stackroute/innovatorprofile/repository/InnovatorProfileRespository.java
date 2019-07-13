package com.stackroute.innovatorprofile.repository;

import com.stackroute.innovatorprofile.domain.InnovatorProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InnovatorProfileRespository extends MongoRepository<InnovatorProfile,String> {
    public InnovatorProfile findByEmailId(String emailId);
}
