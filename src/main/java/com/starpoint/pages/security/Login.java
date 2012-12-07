package com.starpoint.pages.security;

import com.starpoint.pages.Index;
import com.starpoint.domain.User;
import com.starpoint.security.AuthenticationException;
import com.starpoint.security.PublicResource;
import com.starpoint.services.Authenticator;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;
import org.slf4j.Logger;

/**
 */
@PublicResource
@Import(stylesheet = "context:/style/broken.css")
public class Login {
    @Inject
    private Logger logger;

    @Inject
    private Authenticator authenticator;

    @Inject
    private Cookies cookies;

    @Property
    @Validate("required, maxlength=64, email")
    private String username;

    @Property
    @Validate("required, maxlength=64")
    private String password;

    @Component(id = "loginForm")
    private Form loginForm;

    void onActivate() {
        username = cookies.readCookieValue("username");

        if (username == null) {
            username = "someuser@someemail.com";
            password = "anything";
        }
    }

    void onValidateFromLoginForm() {

        if (loginForm.getHasErrors()) {
            return;
        }

        logger.info("user: " + username +  " password: " + password);

        try {
            User user = authenticator.login(username, password);
            cookies.writeCookieValue("username", username);
        } catch (AuthenticationException ae) {
            loginForm.recordError(ae.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            loginForm.recordError(e.getMessage());
        }
    }

    Object onSuccess() {
        return Index.class;
    }
}
