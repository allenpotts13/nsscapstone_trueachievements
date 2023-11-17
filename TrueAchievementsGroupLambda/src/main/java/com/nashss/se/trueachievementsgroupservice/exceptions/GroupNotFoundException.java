package com.nashss.se.trueachievementsgroupservice.exceptions;

public class GroupNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -8696180123330355057L;
    /**
     * Exception with no message or cause.
     */
    public GroupNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public GroupNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public GroupNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public GroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
