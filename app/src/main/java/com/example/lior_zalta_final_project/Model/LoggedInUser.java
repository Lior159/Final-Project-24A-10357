package com.example.lior_zalta_final_project.Model;

public class LoggedInUser {
    private static User user;

    public static void setUser(User user) {
        LoggedInUser.user = user;
    }

    public static User getUser() {
        return user;
    }

}
