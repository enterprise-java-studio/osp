package com.javastudio.lms.tutorial.api.base;

import com.javastudio.lms.tutorial.model.base.EntityBase;

import java.util.List;

public interface GeneralServiceApi<T extends EntityBase> {
    T create(T generalEntity);

    List<T> findAll();

    T find(Long id);

    void update(T t);

    void delete(T t);
}
