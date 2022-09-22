package com.challenge.druid.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<ValidAge, String> {

  private Integer age;

  public void initialize(ValidAge constraintAnnotation) {}

  public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    return age >= 14;
  }

}
