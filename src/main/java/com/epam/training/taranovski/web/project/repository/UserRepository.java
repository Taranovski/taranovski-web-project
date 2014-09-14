/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository;

import com.epam.training.taranovski.web.project.domain.User;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public interface UserRepository extends TemplateRepository<User> {

    User getByName(String name);

    String getTypeOf(int id);

    boolean validate(User user);

}
