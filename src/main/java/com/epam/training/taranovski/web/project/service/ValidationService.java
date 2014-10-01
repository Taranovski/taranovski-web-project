/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service;

/**
 *
 * @author Alyx
 */
public interface ValidationService {

    boolean passwordIsValid(String password);

    boolean userNameIsValid(String userName);

    public boolean telephoneNumberIsValid(String telephoneNumber);

    public boolean salaryIsValid(String salary);

    public boolean experienceIsValid(String experience);
}
