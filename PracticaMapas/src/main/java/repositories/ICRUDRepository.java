package repositories;

import models.Libro;

import java.util.List;

public interface ICRUDRepository<T, ID> {
    List<T> findAll();

    T insert(T entity);

    T update(T entity);

    T findById(ID id);

    T deleteById(ID id);

    Libro delete(Libro libro);
}
