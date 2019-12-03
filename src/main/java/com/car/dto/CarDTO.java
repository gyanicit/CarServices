package com.car.dto;

import java.io.Serializable;

import com.car.constants.FuelType;
import com.car.validator.CarAgeConstraint;
import com.car.validator.CarNameConstraint;

public class CarDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3342665315081495513L;

	private Integer carId;
	
	@CarNameConstraint(message="{car.name.valid}")
	private String carName;
	
	private double price;
	
	private String model;
	
	@CarAgeConstraint
	private Integer yearOfManufacture;
	
	private FuelType fuelType;
	
	public CarDTO() {
		
	}

	public CarDTO(Integer carId, String carName, double price, String model, Integer yearOfManufacture,
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

	@Override
	public String toString() {
		return "CarDTO [carId=" + carId + ", carName=" + carName + ", price=" + price + ", model=" + model
				+ ", yearOfManufacture=" + yearOfManufacture + ", fuelType=" + fuelType + "]";
	}
	
	
	
}
