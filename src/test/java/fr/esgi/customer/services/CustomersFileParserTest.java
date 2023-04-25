package fr.esgi.customer.services;

import fr.esgi.customer.beans.Customer;
import fr.esgi.customer.exception.CustomersResourceNotFoundException;
import fr.esgi.customer.samples.CustomerSample;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CustomersFileParserTest {

    @Test
    void shouldParse() {
        List<Customer> expectedCustomers = CustomerSample.customers();

        CustomersFileParser customersFileParser = new CustomersFileParser();

        List<Customer> customers = customersFileParser.parse("customers.csv");

        assertThat(customers).containsExactlyInAnyOrderElementsOf(expectedCustomers);
    }

    @Test
    void givenUnknownFilename_shouldThrowCustomersResourceNotFoundException() {
        CustomersFileParser customersFileParser = new CustomersFileParser();

        assertThatExceptionOfType(CustomersResourceNotFoundException.class)
                .isThrownBy(() -> customersFileParser.parse("unknownFile.csv"))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("unknownFile.csv n'a pas été trouvé"));
    }

}
