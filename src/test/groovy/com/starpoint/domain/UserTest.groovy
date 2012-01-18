package com.starpoint.domain

import com.starpoint.services.AppModule
import org.apache.tapestry5.ioc.annotations.SubModule
import spock.lang.Specification
import org.apache.tapestry5.services.TapestryModule
import org.apache.tapestry5.hibernate.HibernateCoreModule
import org.apache.tapestry5.ioc.annotations.Inject
import com.starpoint.business.SlowInitSvc

/**
 */
@SubModule([ TapestryModule, HibernateCoreModule, AppModule ])
class UserTest extends Specification {

    @Inject
    private SlowInitSvc svc;

    def testDoNothing() {
        when:
        svc.doNothing()

        then:
        1==1
    }

    def testCreateUserTest() {
        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 5
        "Kirk"   | 4
        "Scotty" | 6
    }
}
