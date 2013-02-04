package com.starpoint.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

/**
 */
public class TrimDemo {

    @Property
    @Persist
    private String demoValue;

    void onActivate() {
        if (demoValue == null) {
            demoValue = "demo";
        }
    }
}
