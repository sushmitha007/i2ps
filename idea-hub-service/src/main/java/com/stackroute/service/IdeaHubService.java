package com.stackroute.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.stackroute.domain.Idea;
import com.stackroute.domain.ServiceProvider;
import com.stackroute.dto.IdeaDto;
import com.stackroute.exceptions.EntityNotFoundException;
import com.stackroute.exceptions.IdeaAlreadyExistsException;
import com.stackroute.exceptions.NullIdeaException;
import org.springframework.stereotype.Service;


public interface IdeaHubService
{
        Idea addIdea(Idea idea);
        void deleteIdea(String ideaId) throws  EntityNotFoundException;
        public Idea updateIdea(String title, List<ServiceProvider> serviceproviders) throws EntityNotFoundException;
        List<Idea> displayIdea(Idea idea)  throws NullIdeaException;
        void send(Idea idea);
        Optional<Idea> findIdeaById(Idea idea) throws EntityNotFoundException;
        public List<Idea> getByEmailId(String emailId);
        Idea getByIdeaTitle(String title)throws EntityNotFoundException;
        public Idea updateApprovedServiceProvider(IdeaDto ideaDto);


        }

