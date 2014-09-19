/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.service.EncryptionService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oleksandr_Taranovsky
 */
@Service
public class EncryptionServiceImplementation implements EncryptionService {

    @Override
    public String encrypt(String stringToEncrypt) {
        return stringToEncrypt;
    }

}
