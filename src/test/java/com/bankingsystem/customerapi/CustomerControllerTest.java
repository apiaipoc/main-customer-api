package com.bankingsystem.customerapi.controller;

import com.bankingsystem.customerapi.model.CustomerDto;
import com.bankingsystem.customerapi.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService service;

    private CustomerDto customer;

    @BeforeEach
    void setup() {
        customer = new CustomerDto();
        customer.setId("cust-1001");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhone("+1555123456");
    }

    private String[] requiredHeaders() {
        return new String[] {
                "x-messageId", "msg-12345",
                "x-appCorrelationId", "app-12345",
                "x-originatingSystemId", "sys-1234"
        };
    }

    @Test
    void testCreateCustomer() throws Exception {
        Mockito.when(service.createCustomer(any(CustomerDto.class))).thenReturn(customer);

        String json = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "john.doe@example.com",
                    "phone": "+1555123456"
                }
                """;

        mockMvc.perform(post("/customers")
                        .header("x-messageId", "msg-12345")
                        .header("x-appCorrelationId", "app-12345")
                        .header("x-originatingSystemId", "sys-1234")
                        .param("brandSilo", "BRAND1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("cust-1001"))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testGetCustomer() throws Exception {
        Mockito.when(service.getCustomer(anyString())).thenReturn(customer);

        mockMvc.perform(get("/customers/cust-1001")
                        .header("x-messageId", "msg-12345")
                        .header("x-appCorrelationId", "app-12345")
                        .header("x-originatingSystemId", "sys-1234")
                        .param("brandSilo", "BRAND1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("cust-1001"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void testListCustomers() throws Exception {
        Mockito.when(service.listCustomers(any(), any(), any())).thenReturn(List.of(customer));

        mockMvc.perform(get("/customers")
                        .header("x-messageId", "msg-12345")
                        .header("x-appCorrelationId", "app-12345")
                        .header("x-originatingSystemId", "sys-1234")
                        .param("brandSilo", "BRAND1")
                        .param("page", "1")
                        .param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("cust-1001"));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        String json = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "john.doe@example.com",
                    "phone": "+1555123456"
                }
                """;

        mockMvc.perform(put("/customers/cust-1001")
                        .header("x-messageId", "msg-12345")
                        .header("x-appCorrelationId", "app-12345")
                        .header("x-originatingSystemId", "sys-1234")
                        .param("brandSilo", "BRAND1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/cust-1001")
                        .header("x-messageId", "msg-12345")
                        .header("x-appCorrelationId", "app-12345")
                        .header("x-originatingSystemId", "sys-1234")
                        .param("brandSilo", "BRAND1"))
                .andExpect(status().isNoContent());
    }
}
