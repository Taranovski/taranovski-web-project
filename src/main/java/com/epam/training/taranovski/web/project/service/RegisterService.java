/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public interface RegisterService {

    boolean loginAllowed(String name);
    
    boolean passwordAllowed(String password);
    
    boolean register(String name, String password, String type);

}
