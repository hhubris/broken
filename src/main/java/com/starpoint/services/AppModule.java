package com.starpoint.services;

import com.starpoint.business.BusLogic1;
import com.starpoint.business.BusLogic1Impl;
import com.starpoint.business.BusLogic2;
import com.starpoint.business.BusLogic2Impl;
import com.starpoint.domain.SimplePojo;
import com.starpoint.security.AuthenticationFilter;
import com.starpoint.ws.DemoResource;
import com.starpoint.ws.DemoResourceImpl;
import org.apache.log4j.Logger;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.Coercion;
import org.apache.tapestry5.ioc.services.CoercionTuple;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.tynamo.resteasy.ResteasySymbols;

public class AppModule {

    public static void bind(ServiceBinder binder) {
        // Authentication
        binder.bind(Authenticator.class, DummyAuthenticator.class);
        // binder.bind(SlowInitSvc.class, SlowInitSvcImpl.class);
        // binder.bind(BusLogic1.class, BusLogic1Impl.class);
        // binder.bind(BusLogic2.class, BusLogic2Impl.class);
        binder.bind(DemoResource.class, DemoResourceImpl.class);
    }

    /*
    @Match("*Logic")
    public static void adviseTransactions(HibernateTransactionAdvisor advisor, MethodAdviceReceiver receiver) {
        advisor.addTransactionCommitAdvice(receiver);
    }

    public static void contributeHibernateEntityPackageManager(Configuration<String> configuration) {
        configuration.add("com.starpoint.helpdesk.domain");
    }
    */

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
        // configuration.add(SymbolConstants.GZIP_COMPRESSION_ENABLED, "false");

        // configuration.add(HibernateSymbols.DEFAULT_CONFIGURATION, "false");
        configuration.add(SymbolConstants.HMAC_PASSPHRASE, "iQhC2JVzgj8uQpKg");


        configuration.add(ResteasySymbols.MAPPING_PREFIX, "/api");
    }


    /*
    public static void contributeHibernateSessionSource(OrderedConfiguration<HibernateConfigurer> config) {
        config.add("H2MemConfig", new HibernateConfigurer() {
            public void configure(org.hibernate.cfg.Configuration configuration) {
                configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
                configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:db");
                configuration.setProperty("hibernate.connection.username", "sa");
                configuration.setProperty("hibernate.connection.password", "");
                configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            }
        });
    }
    */

    public void contributeIgnoredPathsFilter(Configuration<String> conf) {
        conf.add("^\\/dwr\\/.*");
    }

    /*
    @Startup
    public static void registerShutdownListeners(RegistryShutdownHub shutdownHub, final SlowInitSvc svc) {
        shutdownHub.addRegistryWillShutdownListener( new Runnable() {
            @Override
            public void run() {
                svc.handleShutdown();
            }
        });
    }
    */

    @SuppressWarnings("rawtypes")
    public static void contributeTypeCoercer(Configuration<CoercionTuple> configuration) {

        Coercion<java.lang.String, SimplePojo> toSimplePojo = new Coercion<java.lang.String, SimplePojo>() {

            @Override
            public SimplePojo coerce(String input) {
                return SimplePojo.build(new JSONObject(input));
            }
        };

        configuration.add(new CoercionTuple(java.lang.String.class, SimplePojo.class, toSimplePojo));

    }

    public static void contributeResteasyPackageManager(Configuration<String> configuration)
    {
        configuration.add("com.starpoint.ws");
    }

}