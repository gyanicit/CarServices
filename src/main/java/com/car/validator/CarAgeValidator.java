package com.car.validator;

import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarAgeValidator implements ConstraintValidator<CarAgeConstraint, Integer> {

	@Override
	public void initialize(CarAgeConstraint carAgeConstraint) {
	}

	@Override
	public boolean isValid(Integer carField, ConstraintValidatorContext cxt) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int mfgYear = carField;
		return (currentYear - mfgYear <= 15);
	}

}