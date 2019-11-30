package com.mao;

public class User {
    private String email;
    private String username;
    private int confirm;

    public User(String email, String username,int confirm) {
        this.email = email;
        this.username = username;
        this.confirm = confirm;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", confirm=" + confirm +
                '}';
    }
}
