package jp.ne.paypay;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public class Validator {
    private static jakarta.validation.Validator validator = null;
    private static Validator validatorInstance = null;

    private jakarta.validation.Validator getValidator() {
        if (validator == null) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
        }
        return validator;
    }

    public static Validator getInstance() {
        if (validatorInstance == null) {
            validatorInstance = new Validator();
        }
        return validatorInstance;
    }

    public String validate(Object object) {
        StringBuilder messageBuilder = new StringBuilder();
        Set<ConstraintViolation<Object>> violations = getValidator().validate(object);
        if (violations.isEmpty())
            return null;
        for (final ConstraintViolation<Object> violation : violations) {
            messageBuilder.append(violation.getMessage());
        }
        return messageBuilder.toString();
    }
}
