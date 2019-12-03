package com.car.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.car.constants.CarConstant;

public class CarNameValidator implements ConstraintValidator<CarNameConstraint, String> {

	@Override
	public void initialize(CarNameConstraint carNameConstraint) {
	}

	@Override
	public boolean isValid(String carField, ConstraintValidatorContext cxt) {
		return carField != null && !carField.contains(CarConstant.SPACE)&&carField.matches(CarConstant.ALPHANUMERIAC);
	}

}