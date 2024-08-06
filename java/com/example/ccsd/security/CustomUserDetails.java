package com.example.ccsd.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String fullname;
    private String email;

    public CustomUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities,
                             String fullname, String email) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.fullname = fullname;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
