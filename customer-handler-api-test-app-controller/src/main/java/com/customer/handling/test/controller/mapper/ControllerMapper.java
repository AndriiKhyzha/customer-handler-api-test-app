package com.customer.handling.test.controller.mapper;

import com.customer.handling.test.api.AddressApiRequestModel;
import com.customer.handling.test.service.model.AddressServiceModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ControllerMapper {
        AddressServiceModel map(AddressApiRequestModel addressApiRequestModel);
}
