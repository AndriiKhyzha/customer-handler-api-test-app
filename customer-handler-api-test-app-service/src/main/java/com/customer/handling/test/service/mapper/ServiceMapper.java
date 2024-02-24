package com.customer.handling.test.service.mapper;

import com.customer.handling.service.api.AddressData;
import com.customer.handling.test.service.model.AddressServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    @Mapping(target = "dbId", source = "id")
    AddressData map(AddressServiceModel addressServiceModel);
    @Mapping(target = "id", source = "dbId")
    AddressServiceModel map(AddressData addressData);
}
