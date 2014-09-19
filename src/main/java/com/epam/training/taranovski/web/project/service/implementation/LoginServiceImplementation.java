/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.domain.User;
import com.epam.training.taranovski.web.project.repository.UserRepository;
import com.epam.training.taranovski.web.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oleksandr_Taranovsky
 */
@Service
public class LoginServiceImplementation implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String userName, String password) {
        return userRepository.getByNameAndPassword(userName, password);
    }

}
