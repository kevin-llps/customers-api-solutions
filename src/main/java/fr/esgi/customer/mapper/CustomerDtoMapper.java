package fr.esgi.customer.mapper;

import fr.esgi.customer.beans.Customer;
import fr.esgi.customer.dto.CustomerResponseDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CustomerDtoMapper {

    public List<CustomerResponseDto> mapToDtoList(List<Customer> customers) {
        return customers.stream()
                .map(this::mapToDto)
                .toList();
    }

    private CustomerResponseDto mapToDto(Customer customer) {
        return new CustomerResponseDto(
                customer.companyName(),
                customer.town(),
                customer.address(),
                customer.siret()
        );
    }

}
