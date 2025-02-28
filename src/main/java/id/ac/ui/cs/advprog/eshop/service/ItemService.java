package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface ItemService<T> {
    T create(T item);
    List<T> findAll();
    T findById(String id);
    void update(String id, T item);
    void delete(String id);
}
