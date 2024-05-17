package com.example.FitnessApp.Models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationDTO {
    private String username;
    private String password;
    private String role;

    public String toString(){
        return "Registration info: username: " + this.username+ " password: " + this.password + "role:" +this.role;
    }
}
