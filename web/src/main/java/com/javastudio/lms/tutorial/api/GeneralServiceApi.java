package com.javastudio.lms.tutorial.api;

import com.javastudio.lms.tutorial.dto.DataTransferObject;
import com.javastudio.lms.tutorial.model.base.EntityBase;

import java.util.List;

public interface GeneralServiceApi<T extends DataTransferObject> {
    T create(T generalEntity);

    List<T> findAll();

    T find(Long id);

    void update(T t);

    void delete(T t);
}
