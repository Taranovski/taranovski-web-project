/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.service.PasswordRequirementService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alyx
 */
@Service
public class PasswordRequirementServiceImplementation implements PasswordRequirementService {

    @Override
    public boolean passwordMeetRequirements(String password) {
        return !password.matches("\\s*");
    }

}
