package com.example.FitnessApp;

import com.example.FitnessApp.Models.ApplicationUser;
import com.example.FitnessApp.Models.Role;
import com.example.FitnessApp.Repositories.RoleRepository;
import com.example.FitnessApp.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.util.ClassUtils.isPresent;

@SpringBootApplication
public class FitnessAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(FitnessAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder)
	{
		return args ->{
			if(roleRepository.findByAuthority("COACH").isPresent())
				return;
			Role coachRole = roleRepository.save(new Role("COACH"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(coachRole);

		ApplicationUser coach = new ApplicationUser(1,"coach", passwordEncoder.encode("password"),roles);
		userRepository.save(coach);

	};
	}
}
