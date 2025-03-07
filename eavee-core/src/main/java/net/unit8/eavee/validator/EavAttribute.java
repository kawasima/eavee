package net.unit8.eavee.validator;

import net.unit8.eavee.provider.EavAttributeProvider;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EavValidator.class)
public @interface EavAttribute {
    String value();
    Class<? extends EavAttributeProvider> provider();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "eav";
}
