package com.car.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.car.constants.CarConstant;
import com.car.constants.FuelType;
import com.car.dto.CarDTO;
import com.car.exception.ResourceNotFoundException;
import com.car.model.Car;
import com.car.service.CarService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class CarController {

	@Value("${car.save.success}")
	private String carSaveSuccess;

	@Value("${car.update.success}")
	private String carUpdateSuccess;

	@Value("${car.delete.success}")
	private String carDeleteSuccess;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CarService carService;

	@PostMapping(CarConstant.CAR_URI)
	public ResponseEntity<String> insert(@Valid @RequestBody CarDTO carDTO) {
		carService.insert(carDTO);
		return new ResponseEntity<>(carSaveSuccess, HttpStatus.OK);
	}

	@PutMapping(CarConstant.CAR_URI_WITH_ID)
	public ResponseEntity<Car> update(@PathVariable Integer carId, @Valid @RequestBody CarDTO carDTO) {
		Car car=carService.update(carId,carDTO);
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	@DeleteMapping(CarConstant.CAR_URI_WITH_ID)
	public ResponseEntity<String> delete(@PathVariable Integer carId) {
		carService.delete(carId);
		return new ResponseEntity<>(carDeleteSuccess, HttpStatus.OK);
	}

	@GetMapping(CarConstant.CAR_URI)
	@HystrixCommand(fallbackMethod = "fallbackDefaultCarList", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
	public ResponseEntity<List<Car>> getDetails() {
		List<Car> cars=carService.getDetails();
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}

	@GetMapping(CarConstant.CAR_URI_WITH_ID)
	@HystrixCommand(fallbackMethod = "fallbackDefaultCar", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
	public ResponseEntity<CarDTO> getDetailsById(@PathVariable Integer carId) {
		Car car=carService.getDetailsById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
		CarDTO carDTO = modelMapper.map(car, CarDTO.class);
		return new ResponseEntity<>(carDTO, HttpStatus.OK);
	}
	
	@SuppressWarnings("unused")
	private ResponseEntity<CarDTO> fallbackDefaultCar(Integer carId){
		CarDTO carDTO = new CarDTO(-1, "DefaultCarName", 0.00, "DefaultModel", 0000, FuelType.ELECTRIC);
		return new ResponseEntity<>(carDTO, HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<List<Car>> fallbackDefaultCarList(){
		List<Car> cars=new ArrayList<>();
		cars.add(new Car(-1, "DefaultCarName", 0.00, "DefaultModel", 0000, FuelType.ELECTRIC));
		cars.add(new Car(-2, "DefaultCarName", 0.00, "DefaultModel", 0000, FuelType.ELECTRIC));
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}
}
