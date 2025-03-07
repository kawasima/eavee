package net.unit8.eavee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void toJson() throws IOException {
        Person p = new Person();
        p.setId(1L);
        Map<String, Object> profiles = new HashMap<>();
        profiles.put("email", "abc@example.com");
        p.setProfiles(profiles);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(p);
        Map<String, Object> result = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });
        assertThat(result).containsEntry("email", "abc@example.com");
    }
}
