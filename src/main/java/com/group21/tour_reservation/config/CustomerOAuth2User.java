package com.group21.tour_reservation.config;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;

public class CustomerOAuth2User implements OAuth2User, Serializable {
    private String clientName;
    private static final long serialVersionUID = 1L; // serialVersionUID cho tuần tự hóa

    private final OAuth2User delegate;

    public CustomerOAuth2User(OAuth2User delegate, String clientName) {
        this.delegate = delegate;
        this.clientName = clientName;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return delegate.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return delegate.getAuthorities();
    }

    @Override
    public String getName() {
        return delegate.getAttribute("name");
    }

    public String getEmail() {
        return delegate.getAttribute("email");
    }

    public String getfullName() {
        return delegate.getAttribute("name");
    }

    public String getClientName() {
        return this.clientName;
    }
}