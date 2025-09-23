package com.bankingsystem.customerapi;

import com.bankingsystem.customerapi.entity.Address;
import com.bankingsystem.customerapi.model.AddressDto;
import com.bankingsystem.customerapi.model.CustomerDto;
import com.bankingsystem.customerapi.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    private CustomerService service;

    private CustomerDto customer;

    @BeforeEach
    void setup() {
        AddressDto address = new AddressDto();
        //address.setType(Address.AddressType.RESIDENTIAL);
        address.setLine1("123 Main St");
        address.setCity("New York");
        address.setCountry("USA");

        customer = new CustomerDto();
        customer.setId("cust-1001");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhone("+1555123456");
        customer.setAddresses(List.of(address));
    }

    @Test
    void testCreateCustomer() throws Exception {
        Mockito.when(service.createCustomer(any(CustomerDto.class))).thenReturn(customer);

        String json = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "dateOfBirth": "1985-06-15",
                    "email": "john.doe@example.com",
                    "phone": "+1555123456",
                    "addresses": [{
                        "type": "RESIDENTIAL",
                        "line1": "123 Main St",
                        "city": "New York",
                        "country": "USA"
                    }]
                }
                """;

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("cust-1001"))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testGetCustomer() throws Exception {
        Mockito.when(service.getCustomer(anyString()));

        mockMvc.perform(get("/customers/cust-1001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("cust-1001"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/cust-1001"))
                .andExpect(status().isNoContent());
    }
}
