package com.javastudio.lms.tutorial.service;

import com.javastudio.lms.tutorial.dao.UserDao;
import com.javastudio.lms.tutorial.model.to.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserService {

    @EJB
    UserDao userDao;

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return null;
    }

    public void save(User user) {
        userDao.save(user);
    }

}
