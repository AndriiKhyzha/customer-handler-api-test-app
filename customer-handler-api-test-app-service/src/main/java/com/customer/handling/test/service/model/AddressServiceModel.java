package com.customer.handling.test.service.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressServiceModel {

    Integer id;
    String country;
    String city;
    String street;
    String number;
}
