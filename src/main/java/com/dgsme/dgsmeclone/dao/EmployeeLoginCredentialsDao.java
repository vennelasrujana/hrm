package com.dgsme.dgsmeclone.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dgsme.dgsmeclone.dto.EmployeeLoginCredentialsDto;
import com.dgsme.dgsmeclone.repository.EmployeeLoginCredentialsRepository;

@Component
public class EmployeeLoginCredentialsDao {

    @Autowired
    private EmployeeLoginCredentialsRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeLoginCredentialsDto saveCredentials(EmployeeLoginCredentialsDto credentialsDto) {
        String originalPassword = credentialsDto.getEmployeePassword();
        String hashedPassword = passwordEncoder.encode(originalPassword);
        credentialsDto.setEmployeePassword(hashedPassword);
        return repository.save(credentialsDto);
    }

    public EmployeeLoginCredentialsDto validateAndFetchUser(String email, String password) {
        Optional<EmployeeLoginCredentialsDto> userOpt = repository.findByEmployeeEmail(email.trim());
        if (userOpt.isPresent()) {
            EmployeeLoginCredentialsDto user = userOpt.get();
            System.out.println("Incoming password: '" + password + "' length: " + password.length());
            System.out.println("Stored hash: " + user.getEmployeePassword());
            System.out.println("Encode pass: "+passwordEncoder.encode(password));
            System.out.println("Match: " + passwordEncoder.matches(password, user.getEmployeePassword()));

            if (passwordEncoder.matches(password, user.getEmployeePassword())) {
                return user;
            }
        }
        return null;
    }

    public void deleteEmployeeCredentials(String id) {
        repository.deleteByEmployeeId(id);
    }

}
