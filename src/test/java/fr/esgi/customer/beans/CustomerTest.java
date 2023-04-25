package fr.esgi.customer.beans;

import fr.esgi.customer.samples.CustomerSample;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerTest {

    private static final String[] HEADERS = {
            "Id",
            "Raison sociale",
            "Ville",
            "Adresse",
            "Numéro SIRET"
    };

    @Test
    void shouldCreate() {
        String[] csvValues = {"46890", "New Smart IT", "Paris", "77 Rue des roses", "59169271800038"};
        Customer expectedCustomer = CustomerSample.oneCustomer();

        CSVRecord csvRecord = mock(CSVRecord.class);

        for (int i = 0; i < HEADERS.length; i++) {
            when(csvRecord.get(HEADERS[i])).thenReturn(csvValues[i]);
        }

        Customer customer = Customer.create(csvRecord, HEADERS);

        assertThat(customer).isEqualTo(expectedCustomer);
    }

}
