package com.based.genzedong.BasedReddit.services;

import com.based.genzedong.BasedReddit.daos.UserDAO;
import com.based.genzedong.BasedReddit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO;


    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public void addUser(User user){
        userDAO.addUser(user);
    }
}
