package com.starpoint.business;

import org.apache.tapestry5.ioc.annotations.Inject;

/**
 */
public class BusLogic1Impl implements BusLogic1 {

    @Inject
    private BusLogic2 busLogic2;

    @Override
    public String delegateAMethod() {
        return busLogic2.someSillyString();
    }

    @Override
    public int someTestableMethod(int someValue) {
        if (someValue > 0) {
            return someValue / 9;
        } else {
            return someValue * -9;
        }
    }
}
