package com.starpoint.business

import spock.lang.Specification
import com.starpoint.services.MockService
import org.apache.tapestry5.ioc.annotations.Inject
import org.apache.tapestry5.ioc.annotations.SubModule
import org.apache.tapestry5.ioc.test.TestUtils

/**
 */
class BusLogic1Test extends Specification {

    private BusLogic1 busLogic1;

    def "setup"() {
        BusLogic2 mock = Mock(BusLogic2)
        mock.someSillyString() >> "Hello World"
        busLogic1 = TestUtils.create(BusLogic1Impl.class, "busLogic2", mock)
    }

    def "test something"() {
        expect: "that our mock method gets called"
        "Hello World" == busLogic1.delegateAMethod()
    }
}
