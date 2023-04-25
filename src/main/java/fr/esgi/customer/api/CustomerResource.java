package fr.esgi.customer.api;

import fr.esgi.customer.beans.Customer;
import fr.esgi.customer.dto.CustomerResponseDto;
import fr.esgi.customer.mapper.CustomerDtoMapper;
import fr.esgi.customer.services.CustomersFileParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/customers")
public class CustomerResource {

    private final CustomersFileParser customersFileParser;
    private final CustomerDtoMapper customerDtoMapper;

    @Inject
    public CustomerResource(CustomersFileParser customersFileParser,
                            CustomerDtoMapper customerDtoMapper) {
        this.customersFileParser = customersFileParser;
        this.customerDtoMapper = customerDtoMapper;
    }

    @GET
    public List<CustomerResponseDto> getCustomers() {
        List<Customer> customers = customersFileParser.parse("customers.csv");

        return customerDtoMapper.mapToDtoList(customers);
    }

}
