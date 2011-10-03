package com.starpoint.security;

import com.starpoint.services.Authenticator;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.*;
import org.slf4j.Logger;

import java.io.IOException;

/**
 */
public class AuthenticationFilter implements ComponentRequestFilter {

    private final PageRenderLinkSource renderLinkSource;

    private final ComponentSource componentSource;

    private final Request request;

    private final Response response;

    private final Authenticator authenticator;

    @SuppressWarnings("unused")
    private final Logger logger;

    private static final String LOGIN_PAGE = "security/Login";

    private static final String LOGOUT_PAGE = "security/Logout";

    private static final String REDIRECT_AFTER_LOGIN = "__redirect__after__login__";

    public AuthenticationFilter(PageRenderLinkSource renderLinkSource, ComponentSource componentSource,
                                Request request, Response response,
                                Authenticator authenticator, Logger logger) {
        this.renderLinkSource = renderLinkSource;
        this.componentSource = componentSource;
        this.request = request;
        this.response = response;
        this.authenticator = authenticator;
        this.logger = logger;
    }

    public void handleComponentEvent(ComponentEventRequestParameters parameters,
                                     ComponentRequestHandler handler) throws IOException {

        if (dispatchedToLoginPage(parameters.getActivePageName())) {
            return;
        }

        handler.handleComponentEvent(parameters);
    }

    public void handlePageRender(PageRenderRequestParameters parameters,
                                 ComponentRequestHandler handler) throws IOException {

        if (dispatchedToLoginPage(parameters.getLogicalPageName())) {
            return;
        }

        handler.handlePageRender(parameters);
    }

    private boolean dispatchedToLoginPage(String pageName) throws IOException {

        if (authenticator.isLoggedIn()) {

            String redirect = (String) request.getSession(false).getAttribute(REDIRECT_AFTER_LOGIN);

            if (redirect != null) {
                request.getSession(false).setAttribute(REDIRECT_AFTER_LOGIN, null);
                Link link = renderLinkSource.createPageRenderLink(redirect);
                response.sendRedirect(link);
                return true;
            }

            // Logged user should not go back to Signin
            if (LOGIN_PAGE.equalsIgnoreCase(pageName)) {
                response.sendRedirect("Index");
                return true;
            }

            return false;
        }

        Component page = componentSource.getPage(pageName);
        if (page.getClass().isAnnotationPresent(PublicResource.class)) {
            return false;
        }

        if (pageName != null && !pageName.equals(LOGOUT_PAGE)) {
            request.getSession(true).setAttribute(REDIRECT_AFTER_LOGIN, pageName);
        }

        Link link = renderLinkSource.createPageRenderLink(LOGIN_PAGE);
        response.sendRedirect(link);

        return true;
    }

}
