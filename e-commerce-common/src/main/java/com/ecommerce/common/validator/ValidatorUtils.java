package com.ecommerce.common.validator;

import com.ecommerce.common.exception.ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @program: supplycenter
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-03-01 13:24
 */
public class ValidatorUtils {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * 
     * @param object
     *            待校验对象
     * @param groups
     *            待校验的组
     */
    public static void validateEntity(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = constraintViolations.iterator().next();
            StringBuilder sb = new StringBuilder(constraint.getPropertyPath().toString());
            sb.append(" ").append(constraint.getMessage());
            throw new ValidationException("BAD_REQUEST",sb.toString());
        }
    }

}
