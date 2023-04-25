package fr.esgi.customer.api;

import fr.esgi.customer.beans.Customer;
import fr.esgi.customer.dto.CustomerResponseDto;
import fr.esgi.customer.mapper.CustomerDtoMapper;
import fr.esgi.customer.services.CustomersFileParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static fr.esgi.customer.samples.CustomerSample.customers;
import static fr.esgi.customer.samples.CustomerSampleDto.customerResponseDtoList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerResourceTest {

    @InjectMocks
    private CustomerResource customersResource;

    @Mock
    private CustomersFileParser customersFileParser;

    @Mock
    private CustomerDtoMapper customerDtoMapper;

    @Test
    void shouldGetCustomers() {
        List<CustomerResponseDto> expectedCustomerResponseDtoList = customerResponseDtoList();
        List<Customer> expectedCustomers = customers();

        when(customersFileParser.parse("customers.csv")).thenReturn(expectedCustomers);
        when(customerDtoMapper.mapToDtoList(expectedCustomers)).thenReturn(expectedCustomerResponseDtoList);

        List<CustomerResponseDto> customers = customersResource.getCustomers();

        assertThat(customers).containsExactlyInAnyOrderElementsOf(expectedCustomerResponseDtoList);

        verify(customersFileParser).parse("customers.csv");
        verify(customerDtoMapper).mapToDtoList(expectedCustomers);

        verifyNoMoreInteractions(customersFileParser, customerDtoMapper);
    }

}
