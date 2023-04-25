package fr.esgi.customer.exception;

public class MissingCustomerIdException extends RuntimeException {
    public MissingCustomerIdException(String message) {
        super(message);
    }
}
