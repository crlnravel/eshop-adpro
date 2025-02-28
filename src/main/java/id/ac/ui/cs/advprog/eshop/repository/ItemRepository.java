package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface ItemRepository<T> {
    T create(T car);
    Iterator<T> findAll();
    T findById(String id);
    T update(String id, T updatedItem);
    void delete(String id);
}
