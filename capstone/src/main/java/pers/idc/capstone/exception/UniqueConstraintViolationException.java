package pers.idc.capstone.exception;

public class UniqueConstraintViolationException extends RuntimeException {
    public UniqueConstraintViolationException(String constraintName, String value) {
        super(String.format("%s %s is already in use.", constraintName, value));
    }
}
