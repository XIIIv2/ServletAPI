package icu.xiii.app.service;

import java.util.List;

public interface BaseService<T> {

    void create(T obj);

    List<T> getAll();

    T getById(Long id);

    void update(T obj);

    void deleteById(Long id);
}
