package com.dgsme.dgsmeclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgsme.dgsmeclone.dto.AdminDto;
import com.dgsme.dgsmeclone.dto.EmployeeLoginCredentialsDto;
import com.dgsme.dgsmeclone.dto.LoginResponseDto;
import com.dgsme.dgsmeclone.repository.AdminRepository;
import com.dgsme.dgsmeclone.service.AdminService;
import com.dgsme.dgsmeclone.util.JwtUtil;
import com.dgsme.dgsmeclone.util.ResponseStructure;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminRepository repo;

     @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/create")
    public ResponseEntity<ResponseStructure<AdminDto>> createCredentials(@RequestBody AdminDto credentials) {
        
        AdminDto savedCredentials = repo.save(credentials);

        ResponseStructure<AdminDto> responseStructure = new ResponseStructure<>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Credentials created successfully");
        responseStructure.setData(savedCredentials);

        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
    }

    @PostMapping("/validate")
public ResponseEntity<ResponseStructure<LoginResponseDto>> validateCredentials(@RequestBody AdminDto credentials) {
    AdminDto validAdmin = repo.findByAdminEmail(credentials.getAdminEmail());

    ResponseStructure<LoginResponseDto> responseStructure = new ResponseStructure<>();

    if (validAdmin != null && validAdmin.getAdminPassword().equals(credentials.getAdminPassword())) {
        String token = jwtUtil.generateToken(validAdmin.getAdminEmail(), validAdmin.getAdminRole());

        LoginResponseDto response = new LoginResponseDto();
        response.setToken(token);
        response.setAdmin(validAdmin); 

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

}
