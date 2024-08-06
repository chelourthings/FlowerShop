package com.example.ccsd.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    private String fullname;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;

    public User(){

    }

    public User(String username, String email, String password, String fullname,
                String addressLine1, String addressLine2, String city, String state, String postalCode) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFormattedAddress() {
        return String.format("Name: %sAddress: %s, %s, %s, %s, %s",
                fullname, addressLine1, addressLine2, postalCode, city, state);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password +
                ", fullname=" + fullname + ", email=" + email + ", addressLine1="+addressLine1+", addressLine2="
                + addressLine2 + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode + "]";
    }
}


