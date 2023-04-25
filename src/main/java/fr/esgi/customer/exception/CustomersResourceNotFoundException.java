package fr.esgi.customer.exception;

public class CustomersResourceNotFoundException extends RuntimeException {

    public CustomersResourceNotFoundException(String message) {
        super(message);
    }

}
