package fr.esgi.customer.dto;

public record CustomerResponseDto(String companyName,
                                  String town,
                                  String address,
                                  String siret) {
}
