package com.stackroute.controller;


import com.stackroute.domain.Idea;
import com.stackroute.domain.ServiceProvider;
import com.stackroute.dto.IdeaDto;
import com.stackroute.exceptions.EntityNotFoundException;
import com.stackroute.exceptions.IdeaAlreadyExistsException;
import com.stackroute.exceptions.NullIdeaException;
import com.stackroute.service.IdeaHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class IdeaHubController {

    private IdeaHubService ideaHubService;


    @Autowired
    public IdeaHubController(IdeaHubService ideaService) {

        this.ideaHubService = ideaService;
    }

    @PostMapping("/addIdea")
    public ResponseEntity<Idea> addIdea(@RequestBody Idea idea) {
        ResponseEntity responseEntity;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dddd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        dateFormat.format(date);
        idea.setTimestamp(date);

        Idea addedIdea = ideaHubService.addIdea(idea);
        ideaHubService.send(idea);
        return new ResponseEntity<Idea>(addedIdea, HttpStatus.CREATED);
        }


    @GetMapping("/ideas")
    public ResponseEntity<List<Idea>> getIdeas(Idea idea)
    {
        ResponseEntity responseEntity;
        try{
        return new ResponseEntity<List<Idea>>(ideaHubService.displayIdea(idea), HttpStatus.OK);
    }
    catch (NullIdeaException e)
    {
            e.getMessage();
           responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
        catch (Exception e)
        {
            e.getMessage();
             responseEntity=new ResponseEntity("Something went wrong",HttpStatus.CONFLICT);

        }
        return responseEntity;
    }

    @GetMapping("/idea{id}")
    public ResponseEntity<Optional>getIdea(Idea idea) {
        ResponseEntity responseEntity;
        try {
            return new ResponseEntity<>(ideaHubService.findIdeaById(idea), HttpStatus.FOUND);
        }
        catch (EntityNotFoundException e)
        {
            e.getMessage();
            responseEntity=new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("/deleted/{ideaId}")
    public  ResponseEntity<String> deleteIdea(@PathVariable String ideaId) {
        ResponseEntity responseEntity;
        try {
            ideaHubService.deleteIdea(ideaId);
            return new ResponseEntity<String>("successfully deleted", HttpStatus.GONE);
        }
        catch (EntityNotFoundException e)
        { responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);}
        return responseEntity;
    }

    @PutMapping("/idea")
    public ResponseEntity<?> updateIdea(@RequestParam Idea idea) {
        ResponseEntity responseEntity;

        try {
            return new ResponseEntity<Idea>(ideaHubService.updateIdea(idea.getTitle(), idea.getServiceProviders()), HttpStatus.ACCEPTED);
        }
        catch (EntityNotFoundException e)
        { responseEntity=new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/getIdeaByEmailId/{emailId}")
    public ResponseEntity<?> getByEmailId(@PathVariable String emailId)
    {
        return new ResponseEntity<>(ideaHubService.getByEmailId(emailId), HttpStatus.OK);


    }
    @GetMapping("/idea/{title}")
    public ResponseEntity<?> getIdeaByTitle(@PathVariable String title)throws EntityNotFoundException{
        try{
            return new ResponseEntity<Idea>(ideaHubService.getByIdeaTitle(title),HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //method will called by idea-management service to update approved service provider
    @PutMapping("/ideaDto")
    public Idea updateServiceProviders(@RequestBody IdeaDto ideaDto){
        return ideaHubService.updateApprovedServiceProvider(ideaDto);
    }

}