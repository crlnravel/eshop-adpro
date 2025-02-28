package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.Iterator;

public interface CarRepository {
    Car createCar(Car car);
    Iterator<Car> findAll();
    Car findById(String id);
    Car update(String id, Car updatedCar);
    void delete(String id);
}