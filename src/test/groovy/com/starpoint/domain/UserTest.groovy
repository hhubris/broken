package com.starpoint.domain

import com.starpoint.services.AppModule
import org.apache.tapestry5.hibernate.HibernateCoreModule
import org.apache.tapestry5.ioc.annotations.SubModule
import org.apache.tapestry5.services.TapestryModule
import spock.lang.Specification

/**
 */
@SubModule([ TapestryModule, HibernateCoreModule, AppModule ])
class UserTest extends Specification {


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
