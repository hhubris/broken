package com.starpoint.services;

import com.starpoint.domain.User;
import com.starpoint.security.AuthenticationException;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

/**
 */
public class DummyAuthenticator implements Authenticator {

    public static final String AUTH_TOKEN = "authToken";

    @Inject
    private Request request;

    @Override
    public User getCurrentUser() {

        if (isLoggedIn()) {
            return (User)request.getSession(true).getAttribute(AUTH_TOKEN);
        }

        return null;
    }

    @Override
    public boolean isLoggedIn() {
        Session session = request.getSession(false);
        return session != null && session.getAttribute(AUTH_TOKEN) != null;
    }

    @Override
    public User login(String username, String password) throws AuthenticationException {
        User user = new User(username);
        request.getSession(true).setAttribute(AUTH_TOKEN, user);
        return user;
    }

    @Override
    public void logout() {
        Session session = request.getSession(false);

        if (session != null) {
            session.setAttribute(AUTH_TOKEN, null);
            session.invalidate();
        }
    }

    @Override
    public boolean userHasAccessToPage(User user, Class<?> page) {
        return true;
    }
}
