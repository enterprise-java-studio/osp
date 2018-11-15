package com.javastudio.lms.tutorial.service;


import com.javastudio.lms.tutorial.model.to.Role;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Stateless
public class RoleService {

    @Inject
    EntityManager em;

    public void save(Role role) {
        em.persist(role);
    }

    public void delete(Role role) {
        try {
            em.remove(role);
        } catch (EntityNotFoundException e) {
            // It doesn't exist already
        }
    }

    public void delete(Long id) {
        Role role = em.getReference(Role.class, id);
        delete(role);
    }

    public Role find(Long id) {
        return em.find(Role.class, id);
    }

    public List<Role> findAll() {
        return em.createNamedQuery(Role.FIND_ALL, Role.class).getResultList();
    }
}
