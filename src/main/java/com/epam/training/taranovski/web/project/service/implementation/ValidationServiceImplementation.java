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
        if (telephoneNumber == null || telephoneNumber.length() != TELEPHONE_NUMBER_LENGTH || !telephoneNumber.matches("\\d{12}")) {
            valid = false;
        }
        return valid;
    }

    @Override
    public boolean salaryIsValid(String salary) {
        boolean valid = true;

        Double d = null;
        try {
            d = Double.valueOf(salary);
            if (d < 0) {
                valid = false;
            }
        } catch (NumberFormatException e) {
            valid = false;
        }

        return valid;
    }

    @Override
    public boolean experienceIsValid(String experience) {
        int exp = 0;
        boolean success = true;
        if (experience == null) {
            return false;
        }

        try {
            exp = Integer.parseInt(experience);
        } catch (NumberFormatException ex) {
            success = false;
        }

        if (exp < 0) {
            success = false;
        }
        return success;
    }

}
