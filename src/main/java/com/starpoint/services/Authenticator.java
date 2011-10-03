package com.starpoint.services;

import com.starpoint.domain.User;
import com.starpoint.security.AuthenticationException;

/**
 */
public interface Authenticator {

    User getCurrentUser();

    boolean isLoggedIn();

    User login(String username, String password) throws AuthenticationException;

    void logout();

    boolean userHasAccessToPage(User user, Class<?> page);

}
