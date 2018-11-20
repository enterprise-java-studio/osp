package com.javastudio.lms.tutorial.service;

import com.javastudio.lms.tutorial.api.RoleService;
import com.javastudio.lms.tutorial.dao.GenericDao;
import com.javastudio.lms.tutorial.dao.RoleDao;
import com.javastudio.lms.tutorial.model.to.Role;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local({RoleService.class})
public class RoleServiceImpl extends GeneralServiceImpl<Role> implements RoleService {

    @EJB
    RoleDao dao;

    @Override
    public GenericDao<Role> getGenericDao() {
        return dao;
    }
}
