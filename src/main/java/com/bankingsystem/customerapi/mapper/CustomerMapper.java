
package com.bankingsystem.customerapi.mapper;


import com.bankingsystem.customerapi.entity.Address;
import com.bankingsystem.customerapi.entity.Customer;
import com.bankingsystem.customerapi.model.AddressDto;
import com.bankingsystem.customerapi.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // DTO → Entity
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Customer toEntity(CustomerDto customerDto);

    Address toEntity(AddressDto dto);

    // Entity → DTO
    CustomerDto toDTO(Customer customer);

    AddressDto toDTO(Address address);

    List<CustomerDto> toDTOList(List<Customer> customers);

    List<Address> toEntityAddressList(List<AddressDto> addressDtos);

    List<AddressDto> toDTOAddressList(List<Address> addresses);
}