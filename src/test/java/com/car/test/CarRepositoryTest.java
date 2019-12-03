package com.car.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.car.constants.FuelType;
import com.car.exception.ResourceNotFoundException;
import com.car.model.Car;
import com.car.repository.CarRepository;

@SpringBootTest
@DataJpaTest
public class CarRepositoryTest {
	
	@Autowired
	private TestEntityManager  testEntityManager;
	
	@Autowired
	private CarRepository carRepository;
	
	@Test
	public void testSave() {
		Car car=new Car(101, "TestCar", 0.00, "VDI", 0, FuelType.PETROL);
		Car carSaved=testEntityManager.persist(car);
		Car getFromDB=carRepository.findById(carSaved.getCarId()).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carSaved.getCarId()));
		assertThat(getFromDB).isEqualTo(carSaved);
	}

}
