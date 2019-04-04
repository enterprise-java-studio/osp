package com.javastudio.tutorial.service;

import com.javastudio.tutorial.api.GeneralServiceApi;
import com.javastudio.tutorial.dao.GenericDao;
import com.javastudio.tutorial.dto.DataTransferObject;
import com.javastudio.tutorial.model.base.EntityBase;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;


public abstract class GeneralServiceImpl<T extends EntityBase, V extends DataTransferObject> implements GeneralServiceApi<V> {

    @Inject
    private Logger logger;

    private final Class<T> entityType;
    private final Class<V> dtoType;

    public GeneralServiceImpl(Class<T> entityType, Class<V> dtoType) {
        this.entityType = entityType;
        this.dtoType = dtoType;
    }

    public abstract GenericDao<T> getGenericDao();

    protected T to(V dto) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        return mapper.map(dto, entityType);
    }

    protected V dto(T entity) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        return mapper.map(entity, dtoType);
    }

    @Override
    public V create(V entity) {
        logger.info("Try to add entity ...");
        return dto(getGenericDao().create(to(entity)));
    }

    @Override
    public List<V> findAll() {
        List<T> list = getGenericDao().findAll();
        return list.stream().map(this::dto).collect(Collectors.toList());
    }

    @Override
    public V find(Long id) {
        return dto(getGenericDao().findById(id));
    }

    @Override
    public void update(V dto) {
        getGenericDao().update(to(dto));
    }

    @Override
    public void delete(V dto) {
        getGenericDao().remove(to(dto));
    }
}
