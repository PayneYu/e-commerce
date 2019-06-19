package com.ecommerce.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: Supply Center
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-03-13 15:11
 */
@Getter
@Setter
public class ValidationException extends RuntimeException {

    private String status;

    private String message;

    public ValidationException(String status) {
        this.status = status;
        this.message = status;
    }

    public ValidationException(String status, String message) {
        this.status = status;
        this.message = message;
    }

}
