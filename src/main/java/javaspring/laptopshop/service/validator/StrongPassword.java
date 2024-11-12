package javaspring.laptopshop.service.validator;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {
    String message() default "Mật khẩu phải ít nhất 6 kí tự bao gồm kí tự in hoa, in thường , số và kí tự đặc biệt.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
