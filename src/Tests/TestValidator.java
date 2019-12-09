package Tests;

import Models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

public class TestValidator {
    @Test
    void ValidateObjectClientNeg(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Client>> violations = validator.validate(new Client());

        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    void ValidateObjectClientPos(){
        Client client = new Client.Builder()
                .setBDay(LocalDate.of(2019,10, 10))
                .setEmail("dstefurak@email.net")
                .setFName("Dmytro")
                .setLName("Stefurak")
                .setPaymentInfo("0000000000000000")
                .setPhone("380508834011")
                .build();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        Assertions.assertTrue(violations.isEmpty());
    }
}
