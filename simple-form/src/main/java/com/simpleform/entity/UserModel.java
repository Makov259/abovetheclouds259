package com.simpleform.entity;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users_table")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String role;
    String login;
    String password;
    String email;


    public UserModel(String login, String password, String email) {
        this.role = "User";
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserModel() {
        this.role = "User";
    }

    public Integer getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(role, userModel.role) && Objects.equals(login, userModel.login) && Objects.equals(password, userModel.password) && Objects.equals(email, userModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, login, password, email);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
