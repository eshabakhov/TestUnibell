package test.crm.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContactValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidContact {
    String message() default "Неверный формат контакта для указанного типа";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
