package com.example.lior_zalta_final_project.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    String username;
    String password;
    ArrayList<String> categories = new ArrayList<>();
    HashMap<String, Double> budget = new HashMap<>();
    ArrayList<Transaction> transactions = new ArrayList<>();

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public User setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public User setCategories(ArrayList<String> categories) {
        this.categories = categories;
        return this;
    }

    public HashMap<String, Double> getBudget() {
        return budget;
    }

    public User setBudget(HashMap<String, Double> budget) {
        this.budget = budget;
        return this;
    }
}
