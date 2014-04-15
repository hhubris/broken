package com.starpoint.pages;

import com.starpoint.domain.SimplePojo;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class MultiDemo {
    @Inject
    private Logger logger;

    @Property
    List<SimplePojo> groupA;

    @Property
    List<SimplePojo> groupB;

    @Property
    SimplePojo domainObj;

    @Property
    Integer idx;

    @Log
    void onValidateFromMyForm() {
        for (SimplePojo sp : groupA) {
            logger.info("A: " + sp.toString());
        }

        for (SimplePojo sp : groupB) {
            logger.info("B: " + sp.toString());
        }
    }

    @Log
    void setupRender() {
        createGroups();
    }

    @Log
    void onPrepare() {
        createGroups();
    }

    void createGroups() {
        groupA = createGroup();
        groupB = createGroup();
    }

    List<SimplePojo> createGroup() {

        List<SimplePojo> result = new ArrayList<SimplePojo>();
        for (int i = 0; i < 3; i++) {
            result.add(new SimplePojo());
        }

        return result;
    }
}
