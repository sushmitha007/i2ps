package com.stackroute.serviceproviderprofileservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceProvider {
     @Id
     private String emailId;
     private String password;
     String name;
     String domain;
     ArrayList<String> subDomain;
     String role;
     ArrayList<String> skills;
     String about;
     BigDecimal chargePerHour;

}
