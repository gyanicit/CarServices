package com.car.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.car.constants.FuelType;

@Entity
@Table(name="car")
public class Car implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -335051052530977124L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer carId;
	
	@Column(name="car_name")
	private String carName;
	
	@Column(name="price")
	private double price;
	
	@Column(name="model")
	private String model;
	
	@Column(name="manufacture_year")
	private Integer yearOfManufacture;
	
	@Column(name="fuel_type")
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;

	public Car() {
		
	}

	public Car(Integer carId, String carName, double price, String model, Integer yearOfManufacture,
			FuelType fuelType) {
		super();
		this.carId = carId;
		this.carName = carName;
		this.price = price;
		this.model = model;
		this.yearOfManufacture = yearOfManufacture;
		this.fuelType = fuelType;
	}

	public Integer getCarId() {
		return carId;
	}

	public String getCarName() {
		return carName;
	}

	public double getPrice() {
		return price;
	}

	public String getModel() {
		return model;
	}

	public Integer getYearOfManufacture() {
		return yearOfManufacture;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setYearOfManufacture(Integer yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	
	
	
}
