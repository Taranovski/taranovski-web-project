/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.domain.User;
import com.epam.training.taranovski.web.project.repository.UserRepository;
import com.epam.training.taranovski.web.project.service.PasswordRequirementService;
import com.epam.training.taranovski.web.project.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oleksandr_Taranovsky
 */
@Service
public class RegisterServiceImpementation implements RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRequirementService passwordRequirementService;

    @Override
    public boolean register(String name, String password, String type) {
        if (!type.matches("employee") || !type.matches("employer")) {
            return false;
        }

        User user = new User();
        user.setLogin(name);
        user.setPassword(password);
        user.setUserType(type);
        return userRepository.create(user);
    }

    @Override
    public boolean loginAllowed(String name) {
        return !userRepository.nameExistsInDB(name);
    }

    @Override
    public boolean passwordAllowed(String password) {
        return passwordRequirementService.passwordMeetRequirements(password);
    }

}
