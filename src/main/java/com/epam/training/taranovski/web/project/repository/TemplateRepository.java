/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository;

import java.util.List;

/**
 *
 * @author Alyx
 * @param <T>
 */
public interface TemplateRepository<T> {

    T getById(int id);

    List<T> getAll();

    boolean create(T admin);

    boolean update(T admin);

    boolean delete(T admin);
}
