package net.unit8.eavee.validator;

import net.unit8.eavee.EavField;
import net.unit8.eavee.provider.EavAttributeProvider;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * The validator for Entity Attribute Values.
 *
 * @author kawasima
 */
public class EavValidator implements ConstraintValidator<EavAttribute, Map<String, Object>> {
    private EavAttributeProvider provider;
    private List<? extends EavField> fields;

    @Override
    public void initialize(EavAttribute annotation) {
        if (provider == null) {
            try {
                provider = annotation.provider().getConstructor().newInstance();
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        fields = provider.get(annotation.value());
    }

    @Override
    public boolean isValid(Map<String, Object> values, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        return fields.stream()
                .allMatch(field -> {
                    Object value = values.get(field.getName());
                    if (value == null && field.isRequired()) {
                        context.buildConstraintViolationWithTemplate("is required")
                                .addConstraintViolation();
                        return false;
                    }

                    if (!field.getFieldFormat().isValid(Objects.toString(value, null))) {
                        context.buildConstraintViolationWithTemplate("")
                                .addConstraintViolation();
                        return false;
                    }

                    String stringValue = Objects.toString(value, "");
                    if (field.getMaxLength() != null && stringValue.length() > field.getMaxLength()) {
                        ((HibernateConstraintValidatorContext) context).addMessageParameter("value", field.getMaxLength());
                        context.buildConstraintViolationWithTemplate("{javax.validation.constraints.Max.message}")
                                .addPropertyNode(field.getName())
                                .addConstraintViolation();
                        return false;
                    }

                    if (field.getMinLength() != null && stringValue.length() < field.getMinLength()) {
                        ((HibernateConstraintValidatorContext) context).addMessageParameter("value", field.getMinLength());
                        context.buildConstraintViolationWithTemplate("{javax.validation.constraints.Min.message}")
                                .addPropertyNode(field.getName())
                                .addConstraintViolation();
                        return false;
                    }

                    if (field.getRegexp() != null && !Pattern.compile(field.getRegexp()).matcher(stringValue).matches()) {
                        ((HibernateConstraintValidatorContext) context).addMessageParameter("regexp", field.getRegexp());
                        context.buildConstraintViolationWithTemplate("{javax.validation.constraints.Pattern.message}")
                                .addPropertyNode(field.getName())
                                .addConstraintViolation();
                        return false;
                    }

                    if (field.getRegexp() != null && !Pattern.compile(field.getRegexp()).matcher(stringValue).matches()) {
                        ((HibernateConstraintValidatorContext) context).addMessageParameter("regexp", field.getRegexp());
                        context.buildConstraintViolationWithTemplate("{javax.validation.constraints.Pattern.message}")
                                .addPropertyNode(field.getName())
                                .addConstraintViolation();
                        return false;
                    }

                    return true;
                });
    }
}
