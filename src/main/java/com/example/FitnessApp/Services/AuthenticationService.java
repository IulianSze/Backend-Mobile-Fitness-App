package com.example.FitnessApp.Services;

import com.example.FitnessApp.Models.ApplicationUser;
import com.example.FitnessApp.Models.LoginResponseDTO;
import com.example.FitnessApp.Models.Role;
import com.example.FitnessApp.Repositories.RoleRepository;
import com.example.FitnessApp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username, String password, String authority) {
        Optional<ApplicationUser> existingUserOptional = userRepository.findApplicationUserByUsername(username);
        if (existingUserOptional.isPresent()) {
            throw new RuntimeException("Username already exists");
        } else {
            String encodedPassword = passwordEncoder.encode(password);
            Role userRole = roleRepository.findByAuthority(authority).get();
            Set<Role> authorities = new HashSet<>();
            authorities.add(userRole);
            ApplicationUser applicationUser = new ApplicationUser();
            applicationUser.setUsername(username);
            applicationUser.setPassword(encodedPassword);
            applicationUser.setAuthorities(authorities);
            return userRepository.save(applicationUser);
        }
    }
    public LoginResponseDTO loginUser(String username,String password){
        // generate authenticataion token, send over to token service which generate the token and then..

            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            System.out.println(auth);
            // auth is a token
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepository.findApplicationUserByUsername(username).get(),token);

//        }catch(AuthenticationException e){
//            return new LoginResponseDTO(null,"");
//        }
    }
}
