package com.example.event;

// Lab 7b - Problem 1
public class UserService {

    public UserService() {
        System.out.println("UserService bean created by Spring");
    }

    public String registerUser(String name) {
        return "User registered: " + name;
    }
}
