package com.javastudio.lms.tutorial.service;

import com.javastudio.lms.tutorial.api.UserService;
import com.javastudio.lms.tutorial.dao.GenericDao;
import com.javastudio.lms.tutorial.dao.UserDao;
import com.javastudio.lms.tutorial.model.to.User;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local({UserService.class})
public class UserServiceImpl extends GeneralServiceImpl<User> implements UserService {

    @EJB
    UserDao dao;

    @Override
    public GenericDao<User> getGenericDao() {
        return dao;
    }

    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

}
