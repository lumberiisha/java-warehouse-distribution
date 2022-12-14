package com.javawarehousedistribution.config.securityconfig.configservice;

import com.javawarehousedistribution.models.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class MyUserDetails implements UserDetails {
    private final User user;
    public MyUserDetails(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities=new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return simpleGrantedAuthorities;
    }
    @Override
    public String getPassword() {
       return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getUserName();
    }
    public UUID getId(){
        return user.getId();
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
