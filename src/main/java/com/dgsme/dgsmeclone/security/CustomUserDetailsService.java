

package com.dgsme.dgsmeclone.security;

import com.dgsme.dgsmeclone.dto.EmployeeLoginCredentialsDto;
import com.dgsme.dgsmeclone.repository.EmployeeLoginCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeLoginCredentialsRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EmployeeLoginCredentialsDto employee = repository.findByEmployeeEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                employee.getEmployeeEmail(),
                employee.getEmployeePassword(),
                Collections.emptyList() 
        );
    }
}
