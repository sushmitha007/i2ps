package com.stackroute.recommendationservice.service;


import com.stackroute.recommendationservice.domain.UserDTO;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Slf4j
@Service
public class UserServiceImplementation implements UserServices {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private  User user;



    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Collection<User> getAll() {
        return userRepository.getAllUsers();
    }


    @Override
    public String getByName(String name) {
        return userRepository.getNode(name);
    }

    @Override
    public Collection<User> getAllUsersBy(String subDomain) {
        return userRepository.getAllUserDomain(subDomain);
    }

    @Override
    public User saved(User user) {

        return userRepository.save(user);

    }

    @Override
    public User matchUserSubDomain(String subDomainName, String name) {
        return userRepository.matchUserSubDomain(subDomainName,name);
    }


    @RabbitListener(queues = "${serviceNeo4j.queue}")
    public void recievedMessage(UserDTO userDTO) {

        log.info("Received Message: " + userDTO);

        /*
         * this userprofileDTO will have the properties need to set them and assign it to
         * our domain objects to call the methods
         * */

        user.setEmailId(userDTO.getEmailId());
        user.setName(userDTO.getName());
        user.setSubDomain(userDTO.getSubDomain());
        user.setRole(userDTO.getRole());
        userRepository.save(user);
        System.out.println(userDTO.toString());
        log.info("userNode3 is created");
        ArrayList<String> domainList= new ArrayList<>();
        domainList = userDTO.getSubDomain();

        for(int i=0;i<domainList.size();i++) {
            userRepository.matchUserSubDomain(domainList.get(i), userDTO.getName());
        }
        log.info("relationship created");

    }


}
