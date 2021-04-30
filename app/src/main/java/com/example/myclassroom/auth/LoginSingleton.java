package com.example.myclassroom.auth;

public class LoginSingleton {
    private static volatile LoginSingleton instance;


    private LoginRepository loginRepository;

    public LoginSingleton(LoginRepository loginRepository) {
        if(instance != null) return; // Highly cursed, never use
        instance = this;
        this.loginRepository = loginRepository;
    }

    public static LoginSingleton getInstance() {
        return instance;
    }

    public static LoginRepository getRepository() {
        return instance.loginRepository;
    }
}
