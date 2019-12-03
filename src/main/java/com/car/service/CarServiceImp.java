package com.car.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.car.dto.CarDTO;
import com.car.exception.ResourceNotFoundException;
import com.car.model.Car;
import com.car.repository.CarRepository;

@Service
public class CarServiceImp implements CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void insert(CarDTO carDTO) {
		Car car = modelMapper.map(carDTO, Car.class);
		carRepository.save(car);

	}

	@Override
	public Car update1(Car car) {
		return carRepository.save(car);
	}

	@Override
	public Car update(Integer carId, CarDTO carDTO) {
		Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
		car.setCarName(carDTO.getCarName());
		car.setModel(carDTO.getModel());
		car.setPrice(carDTO.getPrice());
		car.setYearOfManufacture(carDTO.getYearOfManufacture());
		car.setFuelType(carDTO.getFuelType());
		return carRepository.save(car);
	}

	@Override
	public void delete(Integer carId) {
		Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
		carRepository.delete(car);

	}

	@Override
	@Cacheable("car")
	public List<Car> getDetails() {
		return carRepository.findAll();
	}

	@Override
	public Optional<Car> getDetailsById(Integer carId) {
		return carRepository.findById(carId);
	}

}
