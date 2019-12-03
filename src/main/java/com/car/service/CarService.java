package com.car.service;

import java.util.List;
import java.util.Optional;

import com.car.dto.CarDTO;
import com.car.model.Car;

public interface CarService {

	public void insert(CarDTO carDTO);

	public Car update1(Car car);

	public Car update(Integer carId, CarDTO carDTO);

	public void delete(Integer carId);

	public List<Car> getDetails();

	public Optional<Car> getDetailsById(Integer carId);

}
