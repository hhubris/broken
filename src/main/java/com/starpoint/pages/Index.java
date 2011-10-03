package com.starpoint.pages;

import com.starpoint.domain.User;
import com.starpoint.pages.security.Login;
import com.starpoint.services.Authenticator;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 */
public class Index {

    @Inject
    private Authenticator authenticator;

    public User getUser() {
        return authenticator.getCurrentUser();
    }

    Object onLogout() {
        authenticator.logout();
        return Login.class;
    }
}
