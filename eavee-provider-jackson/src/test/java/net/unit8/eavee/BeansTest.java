package net.unit8.eavee;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeansTest {
    @Test
    public void test() {
        Person p = new Person();
        p.setId(1L);
        Map<String, Object> profiles = new HashMap<>();
        profiles.put("email", "abc@example.com");
        p.setProfiles(profiles);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(p);

        violations.forEach(v -> System.out.println(v.getMessage()));
    }
}
