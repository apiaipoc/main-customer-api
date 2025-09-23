package com.bankingsystem.customerapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AddressType type;

    @NotBlank
    @Size(min = 5, max = 100)
    private String line1;

    @Size(max = 100)
    private String line2;

    @NotBlank
    @Size(min = 2, max = 50)
    private String city;

    @Size(min = 2, max = 50)
    private String state;

    @Pattern(regexp = "^[0-9A-Za-z\\- ]{3,10}$")
    private String postalCode;

    @NotBlank
    @Size(min = 2, max = 50)
    private String country;

    private boolean isPrimary;

    public enum AddressType { RESIDENTIAL, MAILING, WORK, OTHER }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public AddressType getType() { return type; }
    public void setType(AddressType type) { this.type = type; }

    public String getLine1() { return line1; }
    public void setLine1(String line1) { this.line1 = line1; }

    public String getLine2() { return line2; }
    public void setLine2(String line2) { this.line2 = line2; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public boolean isPrimary() { return isPrimary; }
    public void setPrimary(boolean primary) { isPrimary = primary; }
}
