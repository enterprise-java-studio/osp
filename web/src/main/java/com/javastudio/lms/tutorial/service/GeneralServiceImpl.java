package com.javastudio.lms.tutorial.service;

import com.javastudio.lms.tutorial.api.GeneralServiceApi;
import com.javastudio.lms.tutorial.dao.GenericDao;
import com.javastudio.lms.tutorial.model.base.EntityBase;

import java.util.List;

/**
 * Created by h.mohammadi on 9/16/2017.
 */
public abstract class GeneralServiceImpl<T extends EntityBase> implements GeneralServiceApi<T> {

    public GeneralServiceImpl() {
    }

    public abstract GenericDao<T> getGenericDao();

    @Override
    public T create(T entity) {
        return getGenericDao().create(entity);
    }

    @Override
    public List<T> findAll() {
        return getGenericDao().findAll();
    }

    @Override
    public T find(Long id) {
        return getGenericDao().findById(id);
    }

    @Override
    public void update(T t) {
        getGenericDao().update(t);
    }

    @Override
    public void delete(T t) {
        getGenericDao().remove(t);
    }
}
