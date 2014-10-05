/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.domain.Admin;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.SimpleSessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alyx
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:C:\\Users\\Alyx\\Documents\\NetBeansProjects\\taranovski-web-project\\src\\main\\webapp\\WEB-INF\\persistenceContext.xml")
//@Configuration("file:C:\\Users\\Alyx\\Documents\\NetBeansProjects\\taranovski-web-project\\src\\main\\webapp\\WEB-INF\\persistenceContext.xml")
@Ignore
public class LoginControllerTest {

    @Autowired
    LoginController instance;

    public LoginControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of logout method, of class LoginController.
     */
    @Test
    @Ignore
    public void testLogout() {
        System.out.println("logout");
        ModelAndView modelAndView = new ModelAndView();
        SessionStatus sessionStatus = new SimpleSessionStatus();
        
        instance.logout(modelAndView, sessionStatus);

        assertTrue(sessionStatus.isComplete());
        assertTrue("login.jsp".equals(modelAndView.getViewName()));
    }

    /**
     * Test of login method, of class LoginController.
     */
    @Test
    @Ignore
    public void testLogin() {
        System.out.println("login");
        String userName = "testSubjectAdmin";
        String password = "12345678";

        ModelAndView modelAndView = new ModelAndView();
        
        instance.login(userName, password, modelAndView);
        assertTrue("admin.jsp".equals(modelAndView.getViewName()));
        assertTrue(modelAndView.getModel().containsKey("user"));
        assertTrue("testSubjectAdminName".equals(((Admin) modelAndView.getModel().get("user")).getAdminName()));
    }

}
