/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.domain.User;
import com.epam.training.taranovski.web.project.repository.UserRepository;
import com.epam.training.taranovski.web.project.service.EncryptionService;
import com.epam.training.taranovski.web.project.service.ValidationService;
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
    private ValidationService validationService;

    @Autowired
    private EncryptionService encryptionService;

    @Override
    public boolean register(String name, String password, String type) {

        boolean error = false;
        User user = null;
        
        if (null != type) {
            switch (type) {
                case "employee": {
                    Employee employee = new Employee();
                    employee.setStatus("free");
                    user = employee;
                    
                    break;
                }
                case "employer": {
                    user = new Employer();
                    break;
                }
                default: {
                    error = true;
                    break;
                }
            }
        }

        if (!error) {
            user.setLogin(name);
            user.setPassword(encryptionService.encrypt(password));
            user.setUserType(type);

            return userRepository.create(user);
        } else {
            return false;
        }
    }

    @Override
    public boolean loginAllowed(String name) {
        if (name.matches("\\s*")) {
            return false;
        }
        return !userRepository.nameExistsInDB(name);
    }

    @Override
    public boolean passwordAllowed(String password) {
        return validationService.passwordIsValid(password);
    }

}
