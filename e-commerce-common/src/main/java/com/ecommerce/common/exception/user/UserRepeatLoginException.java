package com.ecommerce.common.exception.user;

/**
 * @author: Huizhe Yu
 * @create: 2019-06-23 22:22
 */
public class UserRepeatLoginException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserRepeatLoginException(Object[] obj) {
        super("user.repeat.login", obj);
    }
}
