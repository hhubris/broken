package com.starpoint.pages;

import com.starpoint.domain.SimplePojo;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.TextField;
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
    SimplePojo[] groupA;

    @Property
    SimplePojo[] groupB;

    @Property
    SimplePojo[] groupC;

    @Property
    SimplePojo domainObj;

    @Property
    Integer idx;

    @Log
    void onValidateFromMyForm() {
        /*
        for (SimplePojo sp : groupA) {
            logger.info("A: " + sp.toString());
        }

        for (SimplePojo sp : groupB) {
            logger.info("B: " + sp.toString());
        }
        */

    }

    @Log
    void onPrepareForRender() {
        createGroups();
    }

    @Log
    void onPrepareForSubmit() {
        createGroups();
    }

    void createGroups() {
        groupA = createGroup();
        groupB = createGroup();
        groupC = createGroup();
    }

    /*
    void onValidateFromId() {
        logger.info(id.toString());
    }
    */

    SimplePojo[] createGroup() {

        SimplePojo[] result = new SimplePojo[3];
        for (int i = 0; i < 3; i++) {
            SimplePojo p = new SimplePojo();
            p.setId(i);
            result[i] = p;
        }

        return result;
    }
}
