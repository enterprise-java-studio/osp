package com.javastudio.tutorial.service;

import com.javastudio.tutorial.api.UserService;
import com.javastudio.tutorial.dao.GenericDao;
import com.javastudio.tutorial.dao.UserDao;
import com.javastudio.tutorial.dto.UserDTO;
import com.javastudio.tutorial.model.to.User;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local({UserService.class})
public class UserServiceImpl extends GeneralServiceImpl<User, UserDTO> implements UserService {

    @EJB
    UserDao dao;

    public UserServiceImpl() {
        super(User.class, UserDTO.class);
    }

    @Override
    public GenericDao<User> getGenericDao() {
        return dao;
    }

    public UserDTO findByUsername(String username) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        return mapper.map(dao.findByUsername(username), UserDTO.class);
    }

    public UserDTO findByEmail(String email) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        return mapper.map(dao.findByEmail(email), UserDTO.class);
    }

}
