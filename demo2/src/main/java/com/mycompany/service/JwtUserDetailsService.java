package com.mycompany.service;

import com.mycompany.model.Employee;
import com.mycompany.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(userId);
        if(employee == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new User(employee.getUsername(),employee.getPassword(), new ArrayList<>()); //getAuthority()
    }
}
