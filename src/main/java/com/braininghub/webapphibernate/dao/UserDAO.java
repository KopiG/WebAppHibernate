/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braininghub.webapphibernate.dao;



import com.braininghub.webapphibernate.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.net.URL;

/**
 *
 * @author kopig
 */
public class UserDAO {

    private static final UserDAO INSTANCE = new UserDAO();

    private UserDAO() {
    }

    public void addUserDetails(String userName, String password, String email,
                               String phone, String city) {
        try {
            // 1. configuring hibernate
            URL resource = getClass().getClassLoader().getResource("hibernate.cfg.xml");
            if (resource == null) {
                throw new IllegalArgumentException("No hibernate.cfg.xml found");
            }
            Configuration configuration = new Configuration().configure(new File(resource.getFile()));

            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setUserName(userName);
            user.setPassword1(password);
            user.setEmail(email);
            user.setCity(city);
            user.setPhone(phone);
            session.save(user);
            transaction.commit();
            System.out.println("\n\n Details Added \n");
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
 
    }

    public static UserDAO getInstance() {
        return INSTANCE;
    }
}
