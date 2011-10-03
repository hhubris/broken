package com.starpoint.services;

import com.starpoint.security.AuthenticationFilter;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.ComponentRequestHandler;

public class AppModule {

    public static void bind(ServiceBinder binder) {
        // Authentication
        binder.bind(Authenticator.class, DummyAuthenticator.class);
    }

    @Contribute(ComponentRequestHandler.class)
    public static void contributeComponentRequestHandler(OrderedConfiguration<ComponentRequestFilter> configuration) {
        configuration.addInstance("RequiresLogin", AuthenticationFilter.class);
    }

    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");

        // The factory default is true but during the early stages of an application
        // overriding to false is a good idea. In addition, this is often overridden
        // on the command line as -Dtapestry.production-mode=false
        configuration.add(SymbolConstants.PRODUCTION_MODE, "false");

        // The application version number is incorprated into URLs for some
        // assets. Web browsers will cache assets because of the far future expires
        // header. If existing assets are changed, the version number should also
        // change, to force the browser to download new versions.
        configuration.add(SymbolConstants.APPLICATION_VERSION, "6.0.0." + System.currentTimeMillis());

        configuration.add(SymbolConstants.COMPRESS_WHITESPACE, "false");
    }
}