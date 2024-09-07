package com.customer.handling.test.controller.mapper;

import com.customer.handling.service.api.AddressData;
import com.customer.handling.test.api.AddressApiRequestModel;
import com.customer.handling.test.api.AddressApiResponseModel;
import com.customer.handling.test.service.model.AddressServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ControllerMapper {

        AddressServiceModel map(AddressApiRequestModel addressApiRequestModel);

        AddressApiResponseModel map(AddressServiceModel addressServiceModel);

        @Mapping(target = "id", source = "dbId")
        AddressApiResponseModel map(AddressData addressData);
}
