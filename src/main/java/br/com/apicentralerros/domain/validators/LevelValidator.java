package br.com.apicentralerros.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LevelValidator implements ConstraintValidator<Level, String> {
    private List<String> acceptedValues;

    @Override
    public void initialize(Level constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if(value == null){
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}
