//UserDto (Data Transfer Object) used to transfer data
//between the controller and service layer
package com.example.ccsd.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserDto {

//    private Long id;
    private String username;
    private String password;
    private String fullname;
    @Email
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;


    public UserDto(String username, String password, String fullname, String email, String addressLine1, String addressLine2, String city, String state, String postalCode) {
        super();
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "UserDto [username=" + username + ", password=" + password +
                ", fullname=" + fullname + ", email=" + email + ", addressLine1="+addressLine1+", addressLine2="
                + addressLine2 + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode + "]";
    }
}
