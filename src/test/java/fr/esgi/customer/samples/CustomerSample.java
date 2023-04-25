package fr.esgi.customer.samples;

import fr.esgi.customer.beans.Customer;

import java.util.List;

public class CustomerSample {

    public static List<Customer> customers() {
        Customer newSmartIt = oneCustomer();

        Customer oxygenIt = new Customer(
                12850,
                "Oxygen IT",
                "Neuilly-sur-Seine",
                "90 rue de la Victoire",
                "42169271850031");

        return List.of(newSmartIt, oxygenIt);
    }

    public static Customer oneCustomer() {
        return new Customer(
                46890,
                "New Smart IT",
                "Paris",
                "77 Rue des roses",
                "59169271800038");
    }

}

