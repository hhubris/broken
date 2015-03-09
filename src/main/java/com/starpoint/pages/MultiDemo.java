package com.starpoint.pages;

import com.starpoint.components.EditObject;
import com.starpoint.domain.SimplePojo;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

/**
 */
public class MultiDemo {
    @Inject
    private Logger logger;

    @Property
    @Persist
    SimplePojo[] groupA;

    @Property
    @Persist
    SimplePojo[] groupB;

    @Property
    @Persist
    SimplePojo[] groupC;

    @Property
    SimplePojo domainObj;

    @Property
    Integer idx;

    @InjectComponent("groupA")
    private EditObject groupAEditor;

    @Log
    void onValidateFromMyForm() {
        for (SimplePojo sp : groupA) {
            logger.info("A: " + sp.toString());
        }
        /*
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
        if (groupA == null) {
            groupA = createGroup();
        }

        if (groupB == null) {
            groupB = createGroup();
        }

        if (groupC == null) {
            groupC = createGroup();
        }
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

    public Boolean getVisible() {
        return idx % 2 == 0;
    }
}
