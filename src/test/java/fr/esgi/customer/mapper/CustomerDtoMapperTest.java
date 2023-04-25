package fr.esgi.customer.mapper;

import fr.esgi.customer.beans.Customer;
import fr.esgi.customer.dto.CustomerResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.esgi.customer.samples.CustomerSample.customers;
import static fr.esgi.customer.samples.CustomerSampleDto.customerResponseDtoList;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerDtoMapperTest {

    @Test
    void shouldMapToDtoList() {
        List<Customer> customers = customers();
        List<CustomerResponseDto> expectedCustomerResponseDtoList = customerResponseDtoList();

        CustomerDtoMapper customerDtoMapper = new CustomerDtoMapper();

        List<CustomerResponseDto> result = customerDtoMapper.mapToDtoList(customers);

        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedCustomerResponseDtoList);
    }


}
