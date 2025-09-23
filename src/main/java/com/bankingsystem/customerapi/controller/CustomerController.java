package com.bankingsystem.customerapi.controller;

import com.bankingsystem.customerapi.api.CustomersApi;
import com.bankingsystem.customerapi.model.CustomerDto;
import com.bankingsystem.customerapi.service.CustomerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class CustomerController implements CustomersApi {

    @Autowired
    private CustomerService service;


    @Override
    public ResponseEntity<CustomerDto> createCustomer(
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "x-messageId", description = "Optional message ID for tracing", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-messageId", required = true) String xMessageId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "x-appCorrelationId", description = "Optional app correlation ID for tracing", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-appCorrelationId", required = true) String xAppCorrelationId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 4, max = 20) @Parameter(name = "x-originatingSystemId", description = "Optional originating system ID", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-originatingSystemId", required = true) String xOriginatingSystemId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 2, max = 10) @Parameter(name = "brandSilo", description = "Filter customers by brand silo", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "brandSilo", required = true) String brandSilo,
            @Parameter(name = "CustomerDto", description = "", required = true) @Valid @RequestBody CustomerDto customerDto
    ) {
        return ResponseEntity.status(201).body(service.createCustomer(customerDto));
    }

    @Override
    public ResponseEntity<List<CustomerDto>> listCustomers(
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "x-messageId", description = "Optional message ID for tracing", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-messageId", required = true) String xMessageId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "x-appCorrelationId", description = "Optional app correlation ID for tracing", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-appCorrelationId", required = true) String xAppCorrelationId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 4, max = 20) @Parameter(name = "x-originatingSystemId", description = "Optional originating system ID", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-originatingSystemId", required = true) String xOriginatingSystemId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 2, max = 10) @Parameter(name = "brandSilo", description = "Filter customers by brand silo", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "brandSilo", required = true) String brandSilo,
            @Min(1) @Parameter(name = "page", description = "Page number for pagination", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false) Integer page,
            @Min(1) @Parameter(name = "limit", description = "Page size for pagination", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false) Integer limit,
            @Parameter(name = "search", description = "Search term", in = ParameterIn.QUERY) @Valid @RequestParam(value = "search", required = false) String search
    )
    {
        return ResponseEntity.ok(service.listCustomers(page, limit, search));
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomer(
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "x-messageId", description = "Optional message ID for tracing", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-messageId", required = true) String xMessageId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "x-appCorrelationId", description = "Optional app correlation ID for tracing", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-appCorrelationId", required = true) String xAppCorrelationId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 4, max = 20) @Parameter(name = "x-originatingSystemId", description = "Optional originating system ID", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-originatingSystemId", required = true) String xOriginatingSystemId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 2, max = 10) @Parameter(name = "brandSilo", description = "Filter customers by brand silo", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "brandSilo", required = true) String brandSilo,
            @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "customerId", description = "Unique identifier of a customer (alphanumeric with dashes)", required = true, in = ParameterIn.PATH) @PathVariable("customerId") String customerId
    ) {
        return ResponseEntity.ok(service.getCustomer(customerId));
    }

    @Override
    public ResponseEntity<Void> updateCustomer(
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "x-messageId", description = "Optional message ID for tracing", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-messageId", required = true) String xMessageId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "x-appCorrelationId", description = "Optional app correlation ID for tracing", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-appCorrelationId", required = true) String xAppCorrelationId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 4, max = 20) @Parameter(name = "x-originatingSystemId", description = "Optional originating system ID", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-originatingSystemId", required = true) String xOriginatingSystemId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 2, max = 10) @Parameter(name = "brandSilo", description = "Filter customers by brand silo", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "brandSilo", required = true) String brandSilo,
            @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "customerId", description = "Unique identifier of a customer (alphanumeric with dashes)", required = true, in = ParameterIn.PATH) @PathVariable("customerId") String customerId,
            @Parameter(name = "CustomerDto", description = "", required = true) @Valid @RequestBody CustomerDto customerDto
    ) {
        service.updateCustomer(customerId, customerDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "x-messageId", description = "Optional message ID for tracing", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-messageId", required = true) String xMessageId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "x-appCorrelationId", description = "Optional app correlation ID for tracing", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-appCorrelationId", required = true) String xAppCorrelationId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 4, max = 20) @Parameter(name = "x-originatingSystemId", description = "Optional originating system ID", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "x-originatingSystemId", required = true) String xOriginatingSystemId,
            @NotNull @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 2, max = 10) @Parameter(name = "brandSilo", description = "Filter customers by brand silo", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "brandSilo", required = true) String brandSilo,
            @Pattern(regexp = "^[a-zA-Z0-9\\-]+$") @Size(min = 5, max = 100) @Parameter(name = "customerId", description = "Unique identifier of a customer (alphanumeric with dashes)", required = true, in = ParameterIn.PATH) @PathVariable("customerId") String customerId
    ) {
        service.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}