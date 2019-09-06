package com.example.herethereproject.src.login.loginModels;

public class LoginBody {

    public String email;

    public String password;

    public LoginBody(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
