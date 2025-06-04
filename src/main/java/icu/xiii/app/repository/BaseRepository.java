package icu.xiii.app.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    void create(T obj);

    Optional<List<T>> getAll();

    Optional<T> getById(Long id);

    void update(T obj);

    boolean deleteById(Long id);
}
