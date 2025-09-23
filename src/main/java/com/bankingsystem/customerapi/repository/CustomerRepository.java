package com.bankingsystem.customerapi.repository;

import com.bankingsystem.customerapi.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Page<Customer> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
}
