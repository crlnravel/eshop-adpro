package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car) {
        return carRepository.create(car);
    }

    @Override
    public List<Car> findAll(){
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> carList = new ArrayList<>();
        carIterator.forEachRemaining(carList::add);
        return carList;
    }

    @Override
    public Car findById(String carId){
        return carRepository.findById(carId);
    }

    @Override
    public void update(String id, Car car) {
        carRepository.update(id, car);
    }

    @Override
    public void delete(String id) {
        carRepository.delete(id);
    }
}
