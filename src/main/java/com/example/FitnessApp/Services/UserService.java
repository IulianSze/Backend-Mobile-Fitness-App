package com.example.FitnessApp.Services;

import com.example.FitnessApp.Models.ApplicationUser;
import com.example.FitnessApp.Models.Role;
import com.example.FitnessApp.Repositories.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class UserService implements UserDetailsService {

   @Autowired
   private PasswordEncoder encoder;

   @Autowired
   private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user details service");

       return userRepository.findApplicationUserByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not valid"));
    }
}
