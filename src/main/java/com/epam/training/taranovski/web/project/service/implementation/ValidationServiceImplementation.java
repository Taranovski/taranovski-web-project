/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.service.ValidationService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alyx
 */
@Service
public class ValidationServiceImplementation implements ValidationService {

    private static final int TELEPHONE_NUMBER_LENGTH = 12;

    @Override
    public boolean passwordIsValid(String password) {
        return password == null ? false : !password.matches("\\s*");
    }

    @Override
    public boolean userNameIsValid(String userName) {
        return userName == null ? false : !userName.matches("\\s*");
    }

    @Override
    public boolean telephoneNumberIsValid(String telephoneNumber) {
        boolean valid = true;
        if (telephoneNumber.length() != TELEPHONE_NUMBER_LENGTH || !telephoneNumber.matches("\\d{12}")) {
            valid = false;
        }
        return valid;
    }

}