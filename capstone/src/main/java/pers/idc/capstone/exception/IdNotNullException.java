package pers.idc.capstone.exception;

public class IdNotNullException extends RuntimeException{
    public IdNotNullException() {
        super("Id should be null.");
    }
}
