package com.javastudio.tutorial.api;

import com.javastudio.tutorial.dto.DataTransferObject;

import java.util.List;

public interface GeneralServiceApi<T extends DataTransferObject> {
    T create(T generalEntity);

    List<T> findAll();

    T find(Long id);

    void update(T t);

    void delete(T t);
}
