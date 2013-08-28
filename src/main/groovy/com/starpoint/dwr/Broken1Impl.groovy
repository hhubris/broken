package com.starpoint.dwr

import com.starpoint.business.BusLogic2
import com.starpoint.dwr.dto.TestDs
import org.directwebremoting.annotations.RemoteMethod
import org.directwebremoting.annotations.RemoteProxy
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject

/**
 */
@RemoteProxy(name = "Broken1")
class Broken1Impl implements Broken1 {

    Logger logger = LoggerFactory.getLogger(Broken1Impl)

    @Inject
    private BusLogic2 busLogic2;

    @RemoteMethod
    public String test1() {
        return busLogic2.someSillyString()
    }

    @RemoteMethod
    public TestDs getDs() {
        return new TestDs(id: 7, name: 'Joe Cool', numbers: [4, 6, 10])
    }

    @RemoteMethod
    public TestDs updateDs(TestDs input) {
        return new TestDs(id: input.id + 1, name: input.name + " Updated", numbers: input.numbers << 77)
    }
}
