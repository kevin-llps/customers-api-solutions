package fr.esgi.customer.samples;

import fr.esgi.customer.dto.CustomerResponseDto;

import java.util.List;

public class CustomerSampleDto {

    public static List<CustomerResponseDto> customerResponseDtoList() {

        CustomerResponseDto newSmartIt = new CustomerResponseDto(
                "New Smart IT",
                "Paris",
                "77 Rue des roses",
                "59169271800038");

        CustomerResponseDto oxygenIt = new CustomerResponseDto(
                "Oxygen IT",
                "Neuilly-sur-Seine",
                "90 rue de la Victoire",
                "42169271850031");

        return List.of(newSmartIt, oxygenIt);
    }

}
