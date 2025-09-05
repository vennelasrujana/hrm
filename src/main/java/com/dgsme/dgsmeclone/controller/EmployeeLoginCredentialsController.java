package com.dgsme.dgsmeclone.controller;

import com.dgsme.dgsmeclone.dto.EmployeeLoginCredentialsDto;
import com.dgsme.dgsmeclone.dto.LoginResponseDto;
import com.dgsme.dgsmeclone.service.EmployeeLoginCredentialsService;
import com.dgsme.dgsmeclone.util.JwtUtil;
import com.dgsme.dgsmeclone.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.Map;



@RestController
@RequestMapping("/api/employee-login")

public class EmployeeLoginCredentialsController {

    @Autowired
    private EmployeeLoginCredentialsService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/validate")
    public ResponseEntity<ResponseStructure<LoginResponseDto>> validateCredentials(@RequestBody EmployeeLoginCredentialsDto credentials) {
        EmployeeLoginCredentialsDto validEmployee = service.validateCredentialsAndFetchUser(
                credentials.getEmployeeEmail(), credentials.getEmployeePassword());

        ResponseStructure<LoginResponseDto> responseStructure = new ResponseStructure<>();

        if (validEmployee != null) {
            // Map<String, Object> claims = new HashMap<>();
            String token = jwtUtil.generateToken(validEmployee.getEmployeeEmail(), validEmployee.getEmployeeRole());

            LoginResponseDto response = new LoginResponseDto();
            response.setToken(token);
            response.setEmployeeRole(validEmployee.getEmployeeRole());
            response.setEmployee(validEmployee);

            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Login successful");
            responseStructure.setData(response);

            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseStructure.setMessage("Invalid credentials");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseStructure<EmployeeLoginCredentialsDto>> createCredentials(@RequestBody EmployeeLoginCredentialsDto credentials) {
        
        EmployeeLoginCredentialsDto savedCredentials = service.save(credentials);

        ResponseStructure<EmployeeLoginCredentialsDto> responseStructure = new ResponseStructure<>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Credentials created successfully");
        responseStructure.setData(savedCredentials);

        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
    }
}
