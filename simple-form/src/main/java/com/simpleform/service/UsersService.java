package com.simpleform.service;


import com.simpleform.entity.UserModel;
import com.simpleform.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;


    public UserModel registerUser(String login, String password, String email, String role ){
        if (login == null || password == null || email == null) {
            return null;
        } else {
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setEmail(email);
            userModel.setRole(role);
            return usersRepository.save(userModel);
        }
    }

    public UserModel registerEmployeeUser(String login, String password, String email) {
        return registerUser(login, password, email, "employeeUser");
    }




    public UserModel authenticate(String login, String password){
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
